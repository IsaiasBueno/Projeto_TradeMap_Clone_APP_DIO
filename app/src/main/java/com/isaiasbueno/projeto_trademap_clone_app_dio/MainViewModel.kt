package com.isaiasbueno.projeto_trademap_clone_app_dio

import androidx.lifecycle.ViewModel
import com.isaiasbueno.projeto_trademap_clone_app_dio.repository.AcaoRepository
class MainViewModel(private val acaoRepository: AcaoRepository) : ViewModel() {

    private val conectorMqtt = ConectorMqtt()

    fun consumirAcoes() {
        conectorMqtt.start { acao ->
            acaoRepository.salvar(acao)
        }
    }

    fun pararCosumirAcoes() {
        conectorMqtt.desligar()
    }

}