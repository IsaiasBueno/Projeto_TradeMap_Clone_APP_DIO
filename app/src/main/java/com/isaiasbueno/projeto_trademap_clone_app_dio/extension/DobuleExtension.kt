package com.isaiasbueno.projeto_trademap_clone_app_dio.extension

import java.text.NumberFormat
import java.util.*
fun Double?.formatarMoeda(): String = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this) ?: "R$ 0,00"