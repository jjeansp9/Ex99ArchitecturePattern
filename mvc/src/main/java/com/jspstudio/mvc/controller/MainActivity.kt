package com.jspstudio.mvc.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jspstudio.mvc.R
import com.jspstudio.mvc.databinding.ActivityMainBinding
import com.jspstudio.mvc.model.Item
import com.jspstudio.mvc.model.ItemModel

class MainActivity : AppCompatActivity() {

    // 1. MVC [ Model - View - Controller ] - 각 파일의 코드별로 역할을 구분하여 작성하는 것이 특징
    //   1) Model       - 데이터를 저장하는 클래스거나
    //                    데이터를 DB/네트워크/파일 등에서 불러오거나 저장하는 등에 작업을 하는 코드를 작성하는 파일들
    //                    [ex: Item 클래스, Person 클래스, Retrofit 작업클래스, DB 작업클래스 ]

    //   2) View        - 사용자가 볼 화면을 구현하는 목적의 코드가 있는 파일들 [ activity_main.xml , fragment_my.xml ]

    //   3) Controller  - 뷰와 모델 사이에서 연결하는 역할클래스,
    //                    클릭같은 이벤트를 처리하여 뷰의 요청에 따라 Model 데이터를 제어하여 뷰에게 보여주는 역할
    //                    [ Activity, Fragment (이 둘은 View의 역할도 함) ]

    // app 모듈에서 만든 Flat Design 에서 MainActivity.kt에 작성한 코드들을 크게 4가지로 역할로 구분해보면..
    // #1 화면구현                                             - view 역할
    // #2 클릭이벤트 처리                                       - controller 역할
    // #3 SharedPreferences 를 이용하여 데이터를 디바이스에        - model 역할
    //    영구적으로 저장하고 불러오는 Business Logic 을 가진 기능

    // 역할별 파일들에 대한 구분을 쉽게 하기위해 java폴더 안에 파일의 역할별로 package 를 나누어 제작해보기

    // # view 참조변수
    lateinit var binding:ActivityMainBinding

    // # model 참조변수
    lateinit var model: ItemModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // # model 객체 생성
        model= ItemModel(this)

        // # view 역할 - activity_main.xml 이 뷰의 역할을 하는 파일이지만,
        //              MainActivity.kt에서 설정하지 않으면 보이지 않으므로, 사실 액티비티는 뷰의 역할도 하고있음
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // # controller 역할 - 클릭이벤트 처리 - view와 model 연결작업 수행
        binding.btnSave.setOnClickListener{
            // model 역할클래스에게 데이터 저장을 요청, 뷰로부터 사용자데이터를 받아와서
            model.saveData(binding.etName.text.toString(), binding.etEmail.text.toString())
        }

        binding.btnLoad.setOnClickListener{
            // model로부터 데이터를 받아와서 뷰에게 보여주도록 요청
            val item:Item = model.loadData()
            binding.tv.text = "${item.name} : ${item.email}"
        }

    }
}

// ## MVC 장점 ##
// 1. 데이터를 제어하는 코드가 Activity/Fragment 클래스 안에 모두 있지 않아서 간결해짐
// 2. 역할별로 코드가 분리되어 있어서 가독성 좋고 코드위치를 구별하여 찾기 쉬워서 유지보수도 용이함
// 3. 이 model 역할을 하는 클래스 안에 어떤 view도 참조하고 있지 않기에
//    view를 변경해도 model은 변경되지 않으며 다른 view[Activity/Fragment]에서 재사용 가능

// ## MVC 단점 ##
// 1. Android 에서는 view 와 controller 의 완전분리가 어려움. Activity 는 view 역할이면서 controller 역할을 함
// 2. view 에서 model 객체를 참조하고 있어서 model 이 변경이 생기면 view에도 영향을 받음
// 3. 규모가 커지면 controller 역할을 하는 Activity 의 코드가 비대해짐

































