package com.ikhdaamel.p10.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhdaamel.p10.model.Mahasiswa
import com.ikhdaamel.p10.repository.RepositoryMhs
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel (private val repoMhs: RepositoryMhs)  : ViewModel(){
    var mhsUIState : HomeUiState by mutableStateOf(HomeUiState.loading)
        private set
    init {
        getMhs()
    }

    fun getMhs(){
        viewModelScope.launch {
            repoMhs.getAllMahasiswa().onStart {
                mhsUIState = HomeUiState.loading
            }
                .catch {
                    mhsUIState = HomeUiState.Error(e = it)
                }
                .collect{
                    mhsUIState = if (it.isEmpty()){
                        HomeUiState.Error(Exception("belum ada data mahasiswa"))
                    } else {
                        HomeUiState.Success(it)
                    }
                }
        }
    }
    fun deleteMhs(mahasiswa: Mahasiswa){
        viewModelScope.launch {
            try {
                repoMhs.deleteMhs(mahasiswa)
            } catch (e: Exception) {
                mhsUIState = HomeUiState.Error(e)
            }
        }
    }
}

//yg harus ada dlm home ini ada 3: loading, menampilkan(sukses), eror
sealed class HomeUiState{
    object loading: HomeUiState()       //pake obj karena merubah data aja gapunya class
    data class Success(val mahasiswa: List<Mahasiswa>) :HomeUiState()       //kalau sukses mengembalikan data mahasiswa
    data class Error(val e: Throwable) : HomeUiState()
}