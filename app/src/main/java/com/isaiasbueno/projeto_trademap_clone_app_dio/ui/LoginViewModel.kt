package com.isaiasbueno.projeto_trademap_clone_app_dio.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isaiasbueno.projeto_trademap_clone_app_dio.domain.ApiService
import com.isaiasbueno.projeto_trademap_clone_app_dio.domain.Usuario
import com.isaiasbueno.projeto_trademap_clone_app_dio.domain.UsuarioLogado
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class LoginViewModel(private val apiService: ApiService) : ViewModel() {

    private val _usuario = MutableLiveData<Usuario>()
    val usuario: LiveData<Usuario> = _usuario

    fun login(login: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val usuario = apiService.login(login)
                UsuarioLogado.usuario = usuario
                _usuario.postValue(usuario)
            }.onFailure {
                Log.i("", it.message.orEmpty())
            }
        }
    }
}