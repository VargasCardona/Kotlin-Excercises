fun divisionPorRestas(dividendo: Int, divisor: Int): Int {
    if (dividendo < divisor) return 0
    
    return 1 + divisionPorRestas(dividendo - divisor, divisor)
}