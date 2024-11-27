import os
import re
from collections import OrderedDict

BASE_DIR = "src/main/java/leetcode"
TEMP_FILE = "temp_solution.java"
PROCESSED_CLASSES = set()
IMPORTS = OrderedDict()  # Para almacenar imports únicos preservando el orden


def read_file(file_path):
    """Lee el contenido de un archivo"""
    with open(file_path, "r", encoding="utf-8") as file:
        return file.readlines()


def write_file(file_path, lines):
    """Escribe contenido en un archivo"""
    with open(file_path, "w", encoding="utf-8") as file:
        file.writelines(lines)


def extract_imports(lines):
    """Extrae y almacena imports únicos que no sean leetcode.*"""
    for line in lines:
        match = re.match(r"import (.*);", line)
        if match and not match.group(1).startswith("leetcode."):
            IMPORTS[match.group(1)] = True


def embed_class(class_path):
    """Embed la clase especificada recursivamente"""
    class_file = os.path.join(BASE_DIR, *class_path.split(".")) + ".java"

    if class_path in PROCESSED_CLASSES:
        return  # Clase ya procesada

    if os.path.exists(class_file):
        print(f"Procesando clase: {class_path}")
        PROCESSED_CLASSES.add(class_path)
        lines = read_file(class_file)

        # Extraer imports que no sean de leetcode
        extract_imports(lines)

        # Filtrar línea de paquete e importaciones
        filtered_lines = [
            re.sub(r"^public class", "class", line)
            for line in lines
            if not line.startswith("package leetcode.") and not line.startswith("import ")
        ]

        # Detectar nuevas clases leetcode.* e incluirlas
        for line in lines:
            match = re.match(r"import leetcode\.(.*);", line)
            if match:
                embed_class(match.group(1))

        return filtered_lines
    else:
        print(f"Advertencia: No se encontró la clase {class_path} en {class_file}.")
        return []


def process_solution(problem_id):
    """Procesa el archivo Solution.java correspondiente al problema"""
    source_file = os.path.join(BASE_DIR, f"Problem{problem_id}/Solution.java")
    if not os.path.exists(source_file):
        print(f"El archivo {source_file} no existe.")
        return

    lines = read_file(source_file)

    # Extraer imports que no sean de leetcode
    extract_imports(lines)

    # Filtrar línea de paquete e importaciones
    solution_lines = [
        line
        for line in lines
        if not line.startswith("package leetcode.") and not line.startswith("import ")
    ]

    # Detectar importaciones de clases leetcode.* en Solution.java
    for line in lines:
        match = re.match(r"import leetcode\.(.*);", line)
        if match:
            solution_lines.extend(embed_class(match.group(1)))

    # Generar el contenido final
    final_lines = [f"import {imp};\n" for imp in IMPORTS] + ["\n"] + solution_lines
    write_file(TEMP_FILE, final_lines)
    print(f"Archivo generado: {TEMP_FILE}")


if __name__ == "__main__":
    import sys

    if len(sys.argv) != 2:
        print("Uso: python script.py <ProblemID>")
        sys.exit(1)

    problem_id = sys.argv[1]
    if not problem_id.isdigit():
        print("El ProblemID debe ser un número.")
        sys.exit(1)

    # Limpiar archivo temporal anterior si existe
    if os.path.exists(TEMP_FILE):
        os.remove(TEMP_FILE)

    process_solution(problem_id)

    # Copiar el contenido al portapapeles automáticamente (si es posible)
    try:
        import pyperclip

        with open(TEMP_FILE, "r", encoding="utf-8") as temp_file:
            pyperclip.copy(temp_file.read())
        print("El contenido se copió al portapapeles.")
    except ImportError:
        print("pyperclip no está instalado. Por favor, copia manualmente el contenido de temp_solution.java.")
