package com.jspstudio.mvvm.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import com.jspstudio.mvvm.model.Item
import com.jspstudio.mvvm.model.ItemModel

class ItemViewModel constructor(context: Context){
    // view 와 연결할 model 역할 클래스 참조변수
    var itemModel:ItemModel = ItemModel(context)

    // 값 변경이 관찰되는 멤버변수
    var model:ObservableField<Item> = ObservableField()

    // 초기화 블럭
    init {
        model.set(Item("aa","aa"))
    }

    // EditText에 글씨가 변경될 때 마다 그 글씨를 저장하는 변수와 기능 만들기
    var name:String=""
    var email:String=""

    fun changeName(name:String){
        this.name= name
    }

    fun changeEmail(email:String){
        this.email= email
    }

    // view의 클릭이벤트 처리 2개
    fun clickedSave(){
        itemModel.saveData(name, email)
    }
    fun clickedLoad(){
        var item:Item= itemModel.loadData()
        model.set(item)
    }
}

























