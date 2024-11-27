#!/bin/bash

# Verifica si se proporcionó el ID del problema
if [ -z "$1" ]; then
  echo "Uso: $0 <ProblemID>"
  exit 1
fi

PROBLEM_ID=$1
SOURCE_FILE="src/main/java/leetcode/Problem$PROBLEM_ID/Solution.java"
TEMP_FILE="temp_solution.java"
BASE_DIR="src/main/java/leetcode"
PROCESSED_CLASSES_FILE="processed_classes.tmp"

# Limpia los archivos temporales previos
> "$TEMP_FILE"
> "$PROCESSED_CLASSES_FILE"

# Función para embeber una clase
function embed_class() {
  local CLASS_PATH=$1
  local CLASS_FILE="$BASE_DIR/${CLASS_PATH//.//}.java"

  echo "Procesando clase: $CLASS_PATH desde archivo $CLASS_FILE"

  # Verifica si la clase ya fue procesada
  if grep -q "^$CLASS_PATH\$" "$PROCESSED_CLASSES_FILE"; then
    echo "Clase $CLASS_PATH ya embebida, omitiendo."
    return
  fi

  if [ -f "$CLASS_FILE" ]; then
    echo "$CLASS_PATH" >> "$PROCESSED_CLASSES_FILE"
    echo "// Embebiendo clase: $CLASS_PATH" >> "$TEMP_FILE"
    grep -v "^package leetcode\." "$CLASS_FILE" | grep -v "^import " | sed 's/^public class/class/' >> "$TEMP_FILE"

    # Busca recursivamente importaciones dentro de la clase embebida
    local SUB_IMPORTS=$(grep "^import leetcode\." "$CLASS_FILE" | sed 's/import leetcode\.//;s/;//')
    for SUB_IMPORT in $SUB_IMPORTS; do
      embed_class "$SUB_IMPORT"
    done
  else
    echo "Advertencia: No se encontró la clase $CLASS_PATH en $CLASS_FILE."
  fi
}

# Función para procesar el archivo hasta que no queden importaciones
function process_file() {
  local FILE=$1
  local REMAINING_IMPORTS=1

  echo "Iniciando procesamiento recursivo para $FILE"

  while [ $REMAINING_IMPORTS -gt 0 ]; do
    # Copia el archivo fuente al archivo temporal sin el package y los imports
    grep -v "^package leetcode\." "$FILE" | grep -v "^import leetcode\." > "$TEMP_FILE"

    # Encuentra las clases importadas desde el package `leetcode`
    IMPORT_CLASSES=$(grep "^import leetcode\." "$FILE" | sed 's/import leetcode\.//;s/;//')

    # Embebe todas las clases importadas
    REMAINING_IMPORTS=0
    for CLASS_PATH in $IMPORT_CLASSES; do
      echo "Encontrada importación: $CLASS_PATH"
      embed_class "$CLASS_PATH"
      REMAINING_IMPORTS=1
    done

    # Actualiza el archivo temporal con las clases embebidas
    mv "$TEMP_FILE" "$FILE"
  done

  echo "Procesamiento recursivo completado para $FILE"
}

# Procesa el archivo inicial
cp "$SOURCE_FILE" "$TEMP_FILE"
process_file "$TEMP_FILE"

# Limpia el archivo temporal de clases procesadas
rm "$PROCESSED_CLASSES_FILE"

# Elimina la línea del paquete
sed -i '/^package leetcode\./d' "$TEMP_FILE"

# Mostrar el resultado final
echo "El archivo generado está listo en $TEMP_FILE."

# Copiar el contenido al portapapeles automáticamente según el sistema operativo
if command -v pbcopy &> /dev/null; then
  # macOS
  cat "$TEMP_FILE" | pbcopy
  echo "El contenido se copió al portapapeles (macOS)."
elif command -v xclip &> /dev/null; then
  # Linux con xclip
  cat "$TEMP_FILE" | xclip -selection clipboard
  echo "El contenido se copió al portapapeles (Linux con xclip)."
else
  echo "No se encontró un comando para copiar al portapapeles. Por favor, copia manualmente desde $TEMP_FILE."
fi
