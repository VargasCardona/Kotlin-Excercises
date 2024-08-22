class Empresa() {
    var razonSocial = "Vacio"
    var nit = "000000-0"
    var direccion = "vacio"

    val dependenciaVentas = "Ventas"
    val dependenciaRecursosHumanos = "Recursos humanos"
    val dependenciaGerencia = "Gerencia"
    val dependenciaOperativo = "Operativo"

    val listaCargos = mutableListOf<Cargo>();
    val listaEmpleados = mutableListOf<Empleado>();
    val listaClientes = mutableListOf<Cliente>();
    val listaDependencias = listOf(dependenciaVentas, dependenciaRecursosHumanos, dependenciaGerencia, dependenciaOperativo)

    init {
        crearCargo(0, "admin", 0)
        crearEmpleado(0, "N/A", "0", "hombre", "N/A", 30.0, 0, 2000, dependenciaGerencia)
    }

    fun crearEmpleado(id: Int, nombreCompleto: String, documento: String, genero: String, correo: String, salario: Double, idCargo: Int, anioIngreso: Int, dependencia: String){
        val empleadoExistente = listaEmpleados.find { it.id == id }
        if (empleadoExistente != null) {
            println("El empleado con id $id ya se encuentra registrado")
            return
        }
        val cargo = listaCargos.find{it.id == idCargo}
        if (cargo == null) {
            println("El cargo no existe")
            return
        }
        if (!listaDependencias.contains(dependencia)) {
            println("La dependencia no existe")
            return
        }
        val empleado = Empleado(id, nombreCompleto, documento, genero, correo, salario, cargo, anioIngreso, dependencia)
        listaEmpleados.add(empleado)
        println("Empleado registrado: $empleado")
    }

    fun actualizarEmpleado(id: Int, nombreCompleto: String, documento: String, genero: String, correo: String, salario: Double, idCargo: Int, anioIngreso: Int, dependencia: String){
        val empleado = listaEmpleados.find{it.id == id}
        if (empleado != null){
            empleado.nombreCompleto = nombreCompleto
            empleado.documento = documento
            empleado.genero = genero
            empleado.correo = correo
            empleado.salario = salario

            val cargo = listaCargos.find{it.id == idCargo}
            if (cargo == null) {
                println("El cargo no existe")
                return
            }
            empleado.cargo = cargo

            if (!listaDependencias.contains(dependencia)) {
                println("La dependencia no existe")
                return
            }
            empleado.dependencia = dependencia

            empleado.anioIngreso = anioIngreso
            println("Empleado editado: $empleado")
        } else {
            println("Persona no encontrada")
        }
    }

    fun eliminarEmpleado(id: Int){
        if (listaEmpleados.size == 1){
            println("La empresa debe tener como minimo un empleado")
            return
        }

        val empleado = listaEmpleados.find{it.id == id}
        if (empleado != null){
            listaEmpleados.remove(empleado)
            println("Empleado eliminado")
        } else {
            println("Empleado no encontrado")
        }
    }

    fun listarEmpleados(){
        listaEmpleados.forEach{println("\n$it")}
    }

    fun cambiarAsociacionSubordinado(idEncargado: Int, idSubordinado: Int, asociar: Boolean){
        val encargado = listaEmpleados.find{it.id == idEncargado}
        if (encargado == null) {
            println("El encargado no existe")
            return
        }
        val subordinado = listaEmpleados.find{it.id == idSubordinado}
        if (subordinado == null) {
            println("El subordinado no existe")
            return
        }

        if (asociar) {
            encargado.listaSubordinados.add(subordinado)
            println("Subordinado asociado correctamente!")
        } else {
            encargado.listaSubordinados.removeIf{it.id == idSubordinado}
            println("Subordinado desasociado correctamente!")
        }
    }

    fun crearCliente(id: Int, nombreCompleto: String, documento: String, genero: String, correo: String, correspondencia: String, telefono: Int){
        val clienteExistente = listaClientes.find { it.id == id }
        if (clienteExistente != null) {
            println("El cliente con id $id ya se encuentra registrado")
            return
        }
        val cliente = Cliente(id, nombreCompleto, documento, genero, correo, correspondencia, telefono)
        listaClientes.add(cliente)
        println("Cliente registrado: $cliente")
    }

    fun actualizarCliente(id: Int, nombreCompleto: String, documento: String, genero: String, correo: String, correspondencia: String, telefono: Int){

        if(listaClientes.size == 0){
            println("No hay clientes registrados")
            return
        }

        val cliente = listaClientes.find{it.id == id}
        if (cliente != null){
            cliente.nombreCompleto = nombreCompleto
            cliente.documento = documento
            cliente.genero = genero
            cliente.correo = correo
            cliente.correspondencia = correspondencia
            cliente.telefono = telefono
            println("Cliente editado: $cliente")
        } else {
            println("Persona no encontrada")
        }
    }

    fun eliminarCliente(id: Int){

        if(listaClientes.size == 0){
            println("No hay clientes para eliminar")
            return
        }

        val cliente = listaClientes.find{it.id == id}
        if (cliente != null){
            listaClientes.remove(cliente)
            println("Cliente eliminado")
        } else {
            println("Cliente no encontrado")
        }
    }

    fun listarClientes(){
        if(listaClientes.size == 0){
            println("No hay clientes registrados")
            return
        }
        listaClientes.forEach{println(it)}
    }

    fun crearCargo(id: Int, nombre: String, nivel: Int){
        val cargoExistente = listaCargos.find { it.id == id }
        if (cargoExistente != null) {
            println("El cargo con id $id ya se encuentra registrado")
            return
        }
        val cargo = Cargo(id, nombre, nivel)
        listaCargos.add(cargo)
        println("Cargo registrado: $cargo")
    }

    fun actualizarCargo(id: Int, nombre: String, nivel: Int){
        if(listaCargos.size == 0){
            println("No hay cargos registrados")
            return
        }

        val cargo = listaCargos.find{it.id == id}
        if (cargo != null){
            cargo.nombre = nombre
            cargo.nivel = nivel
            println("Cargo editado: $cargo")
        } else {
            println("Cargo no encontrado")
        }
    }

    fun eliminarCargo(id: Int){
        if(listaCargos.size == 0){
            println("No hay cargo para eliminar")
            return
        }

        val cargo = listaCargos.find{it.id == id}
        if (cargo != null){
            listaCargos.remove(cargo)
            println("Cargo eliminado")
        } else {
            println("Cargo no encontrado")
        }
    }

    fun listarCargos(){
        if(listaCargos.size == 0){
            println("No hay cargos registrados")
            return
        }
        listaCargos.forEach{println(it)}
    }

    fun hallarNominas(){
        var nominaTotal = 0.0
        listaDependencias.forEach{ dependencia ->
            var nomina = 0.0
            listaEmpleados.forEach{ empleado ->
                if (empleado.dependencia == dependencia){
                    nomina += empleado.salario
                }
            }
            println("Nomina de ${dependencia}: $nomina")
            nominaTotal += nomina
        }

        println("\nLa nomina total de la empresa es: $nominaTotal")
    }

    fun hallarPorcentajeGenero(){

        if(listaClientes.size == 0){
            println("No hay clientes registrados")
            return
        }

        var contadorHombres = 0

        listaClientes.forEach{
            if (it.genero == "hombre"){
                contadorHombres++
            }
        }

        val porcentajeHombres = (contadorHombres * 100) / listaClientes.size
        val porcentajeMujeres = 100 - porcentajeHombres
        println("El porcentaje de hombres es $porcentajeHombres% y el de mujeres $porcentajeMujeres%")
    }

    fun hallarCantidadCargo(){

        listaCargos.forEach{ cargo ->
            var contador = 0
            listaEmpleados.forEach{ empleado ->
                if(empleado.cargo.id == cargo.id){
                    contador++
                }
            }
            val nombreCargo = cargo.nombre
            println("$nombreCargo: $contador")
        }

    }

    fun hallarMasAntiguo(){
        val empleado = listaEmpleados.minByOrNull{it.anioIngreso}
        if (empleado == null) {
            println("No se ha encontrado a ningún empleado")
            return
        }
        println("El empleado mas antiguo es " + empleado.nombreCompleto + " el cual hace parte de la dependencia ${empleado.dependencia} habiendo entrado el año ${empleado.anioIngreso}")
    }

    fun actualizarEmpresa(razon: String, nit: String, direccion: String){
        this.razonSocial = razon
        this.nit = nit
        this.direccion = direccion
        println("Datos actualizados correctamente!")
    }

    fun describirEmpresa(){
        println("Razon social de la empresa: $razonSocial")
        println("NIT de la empresa: $nit")
        println("Dirección de la empresa: $direccion")
    }
}

data class Empleado(var id: Int,
                    var nombreCompleto: String,
                    var documento: String,
                    var genero: String,
                    var correo: String,
                    var salario: Double,
                    var cargo: Cargo,
                    var anioIngreso: Int,
                    var dependencia: String,
                    val listaSubordinados: MutableList<Empleado> = mutableListOf())

data class Cliente(var id: Int,
                   var nombreCompleto: String,
                   var documento: String,
                   var genero: String,
                   var correo: String,
                   var correspondencia: String,
                   var telefono: Int)

data class Cargo(var id: Int,
                 var nombre: String,
                 var nivel: Int)

// ----------------------------------------------------------------------------------------------

fun leerAtributo(mensaje: String): String{
    println("\n$mensaje: ")
    return readln()
}

fun leerDependencia(empresa: Empresa): String{
    var dependencia = ""
    while (!empresa.listaDependencias.contains(dependencia)){
        val dependenciaInput = leerAtributo("Ingrese la dependencia del empleado:"
                +"\n[v]: ventas, [r]: recursos humanos, [g]: gerencia, [o]: operativo").lowercase().trim()
        dependencia = when (dependenciaInput){
            "v" -> empresa.dependenciaVentas
            "r" -> empresa.dependenciaRecursosHumanos
            "g" -> empresa.dependenciaGerencia
            "o" -> empresa.dependenciaOperativo
            else -> ""
        }
    }
    return dependencia
}

fun leerGenero(persona: String): String{
    var genero = ""
    while (!listOf("hombre", "mujer").contains(genero)){
        val generoInput = leerAtributo("Ingrese el género del $persona:"
                +"\n[h]: Hombre, [m]: Mujer").lowercase().trim()
        genero = when (generoInput){
            "h" -> "hombre"
            "m" -> "mujer"
            else -> ""
        }
    }
    return genero
}

// ----------------------------------------------------------------------------------------------

fun main(args: Array<String>) {

    val empresa = Empresa()

    while(true){
        println("\nCRUD EMPRESA")
        println("1. Crud Empleados")
        println("2. Crud Clientes")
        println("3. Crud Cargos")
        println("4. Configuración de Empresa \n")


        val seleccion = leerAtributo("Ingrese su selección")
        when(seleccion){
            "1" -> {
                println("\nCRUD EMPLEADOS")
                println("1. Crear empleado")
                println("2. Editar empleado")
                println("3. Eliminar empleado")
                println("4. Asociar subordinado")
                println("5. Eliminar de subordinación")
                println("6. Listar empleados \n")
                val empleadoSeleccion = leerAtributo("Ingrese su selección")

                when(empleadoSeleccion) {

                    "1" -> {
                        val id = leerAtributo("Ingrese el id del empleado").toIntOrNull() ?: 0
                        val nombreCompleto = leerAtributo("Ingrese el nombre del empleado")
                        val documento = leerAtributo("Ingrese el documento del empleado")
                        val genero = leerGenero("empleado")
                        val correo = leerAtributo("Ingrese el correo del empleado")
                        val salario = leerAtributo("Ingrese el salario del empleado").toDoubleOrNull() ?: 0.0
                        var existeCargo = false
                        var idCargo = 0
                        while (!existeCargo){
                            idCargo = leerAtributo("Ingrese el id del cargo del empleado").toIntOrNull() ?: 0
                            existeCargo = empresa.listaCargos.any { it.id == idCargo }
                            if (!existeCargo){
                                println("El cargo con id $idCargo no existe")
                            }
                        }
                        val anioIngreso = leerAtributo("Ingrese el año de ingreso del empleado").toIntOrNull() ?: 0
                        val dependencia = leerDependencia(empresa)

                        empresa.crearEmpleado(id, nombreCompleto, documento, genero, correo, salario, idCargo, anioIngreso, dependencia)

                    }
                    "2" -> {
                        val id = leerAtributo("Ingrese el id del empleado para editar").toIntOrNull() ?: 0
                        val empleado = empresa.listaEmpleados.find { it.id == id }
                        if (empleado == null) {
                            println("El empleado no existe")
                        } else {
                            val nombreCompleto = leerAtributo("Ingrese el nuevo nombre del empleado")
                            val documento = leerAtributo("Ingrese el documento del empleado")
                            val genero = leerGenero("empleado")
                            val correo = leerAtributo("Ingrese el nuevo correo del empleado")
                            val salario = leerAtributo("Ingrese el nuevo salario del empleado").toDoubleOrNull() ?: 0.0
                            var existeCargo = false
                            var idCargo = 0
                            while (!existeCargo){
                                idCargo = leerAtributo("Ingrese el id del nuevo cargo del empleado").toIntOrNull() ?: 0
                                existeCargo = empresa.listaCargos.any { it.id == idCargo }
                                if (!existeCargo){
                                    println("El cargo con id $idCargo no existe")
                                }
                            }
                            val anioIngreso = leerAtributo("Ingrese el nuevo año de ingreso del empleado").toIntOrNull() ?: 0
                            val dependencia = leerDependencia(empresa)

                            empresa.actualizarEmpleado(
                                id,
                                nombreCompleto,
                                documento,
                                genero,
                                correo,
                                salario,
                                idCargo,
                                anioIngreso,
                                dependencia
                            )
                        }
                    }
                    "3" -> {
                        val id = leerAtributo("Ingrese el id del empleado para eliminar").toIntOrNull() ?: 0
                        empresa.eliminarEmpleado(id)
                    }
                    "4" -> {
                        val idEncargado = leerAtributo("Ingrese el id del empleado encargado").toIntOrNull() ?: 0
                        val idSubordinado = leerAtributo("Ingrese el id del empleado subordinado").toIntOrNull() ?: 0
                        empresa.cambiarAsociacionSubordinado(idEncargado, idSubordinado, true)

                    }
                    "5" -> {
                        val idEncargado = leerAtributo("Ingrese el id del empleado encargado").toIntOrNull() ?: 0
                        val idSubordinado = leerAtributo("Ingrese el id del empleado subordinado").toIntOrNull() ?: 0
                        empresa.cambiarAsociacionSubordinado(idEncargado, idSubordinado, false)
                    }
                    "6" -> empresa.listarEmpleados()

                }
            }
            "2" -> {
                println("\nCRUD CLIENTES")
                println("1. Crear cliente")
                println("2. Editar cliente")
                println("3. Eliminar cliente")
                println("4. Listar clientes \n")
                val clienteSeleccion = leerAtributo("Ingrese su selección")

                when(clienteSeleccion) {

                    "1" -> {
                        val id = leerAtributo("Ingrese el id del cliente").toIntOrNull() ?: 0
                        val nombreCompleto = leerAtributo("Ingrese el nombre del cliente")
                        val documento = leerAtributo("Ingrese el documento del cliente")
                        val genero = leerGenero("cliente")
                        val correo = leerAtributo("Ingrese el correo del cliente")
                        val correspondencia = leerAtributo("Ingrese la correspondencia del cliente")
                        val telefono = leerAtributo("Ingrese el telefono del cliente").toIntOrNull() ?: 0

                        empresa.crearCliente(id, nombreCompleto, documento, genero, correo, correspondencia, telefono)
                    }

                    "2" -> {
                        val id = leerAtributo("Ingrese el id del cliente para editar").toIntOrNull() ?: 0
                        val cliente = empresa.listaClientes.find { it.id == id }
                        if (cliente == null) {
                            println("El cliente no existe")
                        } else {
                            val nombreCompleto = leerAtributo("Ingrese el nuevo nombre del cliente")
                            val documento = leerAtributo("Ingrese el documento del cliente")
                            val genero = leerGenero("cliente")
                            val correo = leerAtributo("Ingrese el nuevo correo del cliente")
                            val correspondencia = leerAtributo("Ingrese la nueva correspondencia del cliente")
                            val telefono = leerAtributo("Ingrese el nuevo telefono del cliente").toIntOrNull() ?: 0

                            empresa.actualizarCliente(id, nombreCompleto, documento, genero, correo, correspondencia, telefono)
                        }
                    }
                    "3" -> {
                        val id = leerAtributo("Ingrese el id del cliente para eliminar").toIntOrNull() ?: 0
                        empresa.eliminarCliente(id)
                    }
                    "4" -> empresa.listarClientes()

                }

            }
            "3" -> {
                println("\nCRUD CARGOS")
                println("1. Crear cargo")
                println("2. Editar cargo")
                println("3. Eliminar cargo")
                println("4. Listar cargos \n")
                val cargoSeleccion = leerAtributo("Ingrese su selección")

                when(cargoSeleccion) {

                    "1" -> {
                        val id = leerAtributo("Ingrese el id del cargo").toIntOrNull() ?: 0
                        val nombre = leerAtributo("Ingrese el nombre del cargo")
                        val nivel = leerAtributo("Ingrese el nivel jerárquico del cargo").toIntOrNull() ?: 0

                        empresa.crearCargo(id, nombre, nivel)
                    }

                    "2" -> {
                        val id = leerAtributo("Ingrese el id del cargo para editar").toIntOrNull() ?: 0
                        val cargo = empresa.listaCargos.find { it.id == id }
                        if (cargo == null) {
                            println("El cargo no existe")
                        } else {
                            val nombre = leerAtributo("Ingrese el nombre del cargo")
                            val nivel = leerAtributo("Ingrese el nivel jerárquico del cargo").toIntOrNull() ?: 0

                            empresa.actualizarCargo(id, nombre, nivel)
                        }
                    }
                    "3" -> {
                        val id = leerAtributo("Ingrese el id del cargo para eliminar").toIntOrNull() ?: 0
                        empresa.eliminarCargo(id)
                    }
                    "4" -> empresa.listarCargos()

                }
            }
            "4" -> {
                println("\nEMPRESA")
                println("1. Actualizar información")
                println("2. Valor nominas")
                println("3. Porcentaje de clientes por sexo")
                println("4. Cantidad de empleados por cargo")
                println("5. Empleado mas antiguo")
                println("6. Describir datos de la empresa")
                val empresaSeleccion = leerAtributo("Ingrese su selección")

                when(empresaSeleccion){
                    "1" -> {
                        val nombre = leerAtributo("Ingrese la razon social de la empresa")
                        val nit = leerAtributo("Ingrese el nuevo nit de la empresa")
                        val direccion = leerAtributo("Ingrese la nueva dirección de la empresa")
                        empresa.actualizarEmpresa(nombre, nit, direccion)
                    }
                    "2" -> empresa.hallarNominas()
                    "3" -> empresa.hallarPorcentajeGenero()
                    "4" -> empresa.hallarCantidadCargo()
                    "5" -> empresa.hallarMasAntiguo()
                    "6" -> empresa.describirEmpresa()
                }

            }
        }
    }
}
