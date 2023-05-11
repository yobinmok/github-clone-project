package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// @Scope 지정: GetItemUseCase 인스턴스 사용 범위
class GetAllUserUseCase @Inject constructor(private val repository: UserRepository) {

    fun execute(): Flow<List<User>> {
        //Log.d("GetItemUseCase", itemList.toString())
        return repository.requestAllUser()
    }

    // invoke를 사용하면 호출부에서 메소드 이름 대신 클래스 이름만으로 호출이 가능하다.
    operator fun invoke(){

    }
}