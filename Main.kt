//Laboratorio # 2 - Programación de plataformas móviles 
//Universidad del Valle de Guatemala

// @Edwing José Gabriel de León García – Carné No. 22809
//@since 26/07/2023
//CC3087 Seccion 31

import java.util.*

// Clase Hobby
class Hobby(
    val titulo: String,
    val descripcion: String,
    val urlPhoto: String?
)

// Enumeración para el estado del perfil
enum class EstadoPerfil {
    Activo, Pendiente, Inactivo
}

// Clase PerfilUsuario
class PerfilUsuario(
    val id: Int,
    val nombres: String,
    val apellidos: String,
    val urlPhoto: String?,
    val edad: Int,
    val correo: String,
    val biografia: String?,
    var estado: EstadoPerfil,
    val hobbies: MutableList<Hobby> = mutableListOf()
) {
    fun agregarHobby(hobby: Hobby) {
        hobbies.add(hobby)
    }
}

fun main() {
    // Lista de perfiles precargados (puedes agregar más perfiles aquí)
    val listaPerfiles = mutableListOf(
        PerfilUsuario(1, "Juan", "Perez", null, 25, "juan@example.com", null, EstadoPerfil.Activo),
        PerfilUsuario(2, "Maria", "Gomez", null, 30, "maria@example.com", null, EstadoPerfil.Pendiente)
    )

    // Menú de opciones
    var opcion: Int
    do {
        println("\n=== Menú de Opciones ===")
        println("1. Crear perfil")
        println("2. Buscar perfil de usuario(s)")
        println("3. Eliminar perfil")
        println("4. Agregar Hobby")
        println("5. Salir")
        print("Ingrese la opción deseada: ")
        opcion = readLine()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> {
                println("\n=== Crear Perfil ===")
                val id = UUID.randomUUID().hashCode() // Generar un ID único
                print("Nombres: ")
                val nombres = readLine() ?: ""
                print("Apellidos: ")
                val apellidos = readLine() ?: ""
                print("URL de la foto de perfil (opcional): ")
                val urlPhoto = readLine()
                print("Edad: ")
                val edad = readLine()?.toIntOrNull() ?: 0
                print("Correo electrónico: ")
                val correo = readLine() ?: ""
                print("Biografía (opcional): ")
                val biografia = readLine()
                print("Estado (Activo/Pendiente/Inactivo): ")
                val estado = readLine()?.let { estadoStr ->
                    EstadoPerfil.values().find { it.name.equals(estadoStr, ignoreCase = true) }
                } ?: EstadoPerfil.Pendiente

                val nuevoPerfil = PerfilUsuario(
                    id, nombres, apellidos, urlPhoto, edad, correo, biografia, estado
                )

                listaPerfiles.add(nuevoPerfil)
                println("Perfil creado exitosamente con ID: $id")
            }

            2 -> {
                println("\n=== Buscar Perfil de Usuario(s) ===")
                print("Ingrese el ID del perfil (opcional): ")
                val idBusqueda = readLine()?.toIntOrNull()
                print("Ingrese nombres y/o apellidos (opcional): ")
                val nombresApellidosBusqueda = readLine()

                val perfilesEncontrados = listaPerfiles.filter { perfil ->
                    idBusqueda?.let { perfil.id == it } ?: true
                }.filter { perfil ->
                    nombresApellidosBusqueda?.let {
                        perfil.nombres.contains(it, ignoreCase = true) ||
                                perfil.apellidos.contains(it, ignoreCase = true)
                    } ?: true
                }

                if (perfilesEncontrados.isNotEmpty()) {
                    perfilesEncontrados.forEach { perfil ->
                        println("\n=== Perfil con ID: ${perfil.id} ===")
                        println("Nombres: ${perfil.nombres}")
                        println("Apellidos: ${perfil.apellidos}")
                        println("URL de la foto de perfil: ${perfil.urlPhoto ?: "No disponible"}")
                        println("Edad: ${perfil.edad}")
                        println("Correo electrónico: ${perfil.correo}")
                        println("Biografía: ${perfil.biografia ?: "No disponible"}")
                        println("Estado: ${perfil.estado}")
                        println("Hobbies:")
                        if (perfil.hobbies.isNotEmpty()) {
                            perfil.hobbies.forEachIndexed { index, hobby ->
                                println("${index + 1}. ${hobby.titulo}")
                                println("   Descripción: ${hobby.descripcion}")
                                println("   URL de la foto: ${hobby.urlPhoto ?: "No disponible"}")
                            }
                        } else {
                            println("No tiene hobbies registrados.")
                        }
                    }
                } else {
                    println("No se encontraron perfiles con la información ingresada.")
                }
            }

            3 -> {
                println("\n=== Eliminar Perfil ===")
                print("Ingrese el ID del perfil a eliminar: ")
                val idEliminar = readLine()?.toIntOrNull()

                val perfilAEliminar = listaPerfiles.find { perfil -> perfil.id == idEliminar }
                if (perfilAEliminar != null) {
                    listaPerfiles.remove(perfilAEliminar)
                    println("Perfil con ID ${perfilAEliminar.id} eliminado exitosamente.")
                } else {
                    println("No se encontró un perfil con el ID ingresado.")
                }
            }

            4 -> {
                println("\n=== Agregar Hobby a Perfil ===")
                print("Ingrese el ID del perfil al que desea agregar el hobby: ")
                val idPerfilAgregarHobby = readLine()?.toIntOrNull()
                val perfilAgregarHobby = listaPerfiles.find { perfil -> perfil.id == idPerfilAgregarHobby }
                if (perfilAgregarHobby != null) {
                    println("Perfil encontrado:")
                    println("Nombres: ${perfilAgregarHobby.nombres}")
                    println("Apellidos: ${perfilAgregarHobby.apellidos}")
                    println("Edad: ${perfilAgregarHobby.edad}")
                    println("Estado: ${perfilAgregarHobby.estado}")
                    println("Hobbies:")
                    if (perfilAgregarHobby.hobbies.isNotEmpty()) {
                        perfilAgregarHobby.hobbies.forEachIndexed { index, hobby ->
                            println("${index + 1}. ${hobby.titulo}")
                            println("   Descripción: ${hobby.descripcion}")
                            println("   URL de la foto: ${hobby.urlPhoto ?: "No disponible"}")
                        }
                    } else {
                        println("No tiene hobbies registrados.")
                    }

                    println("\n=== Agregar Hobby ===")
                    print("Título del hobby: ")
                    val tituloHobby = readLine() ?: ""
                    print("Descripción del hobby: ")
                    val descripcionHobby = readLine() ?: ""
                    print("URL de la foto del hobby (opcional): ")
                    val urlPhotoHobby = readLine()

                    val nuevoHobby = Hobby(tituloHobby, descripcionHobby, urlPhotoHobby)
                    perfilAgregarHobby.agregarHobby(nuevoHobby)
                    println("Hobby agregado exitosamente al perfil.")
                } else {
                    println("No se encontró un perfil con el ID ingresado.")
                }
            }

            5 -> {
                println("Saliendo del programa...")
            }

            else -> {
                println("Opción inválida. Por favor, ingrese una opción válida.")
            }
        }

    } while (opcion != 5)
}
