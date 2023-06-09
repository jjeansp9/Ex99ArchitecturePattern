package com.jspstudio.mvp.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jspstudio.mvp.R
import com.jspstudio.mvp.databinding.ActivityMainBinding
import com.jspstudio.mvp.model.Item
import com.jspstudio.mvp.presenter.MainContract
import com.jspstudio.mvp.presenter.MainPresenter

// MainContract 인터페이스에서 기술한 view 라면 가져야 할 기능들이 설계된 View 인터페이스를 구현
class MainActivity : AppCompatActivity(), MainContract.View {

    // 2. MVP [ Model - View - Presenter ] : view와 model의 완전분리 특징이 가장 두드러짐

    // 1) Model      - MVC패턴의 model과 같은 역할 : 데이터를 저장하는 클래스, 데이터를 제어하는 코드를 가진 클래스들
    //                 [ ex: Item 클래스, Person 클래스, Retrofit 작업클래스, DB 작업클래스 ]

    // 2) View       - 사용자가 볼 화면을 구현하는 목적의 코드가 작성되는 파일들
    //                 [ ex: activity_main.xml, MainActivity.kt, Fragment.kt... ]

    // 3) Presenter  - view와 model 사이에서 연결하는 역할. interface 로 역할을 정해놓기에 특정 객체를 만들어 참조하여
    //                 결합되는 것을 방지함

    // **** 주요 제작 특징 : view와 presenter가 해야할 작업들을 미리 interface를 통해 설계해 놓음으로서 ****
    //                     작업의 역할 구분이 명확하게 보임                                        ****

    // # view 참조변수
    lateinit var binding : ActivityMainBinding

    // # presenter 참조변수
    lateinit var presenter:MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // # presenter 객체 생성 및 참조
        presenter= MainPresenter()
        presenter.initial(this)

        // # view 로서의 역할
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // # view 로서 사용자 이벤트 처리 - presenter 에게 model 의 데이터를 제어해달라고 요청
        binding.btnSave.setOnClickListener{presenter.clickedSave(binding.etName.text.toString(), binding.etEmail.text.toString())}
        binding.btnLoad.setOnClickListener{presenter.clickedLoad()}
    }

    override fun showData(item: Item) {
        binding.tv.text= "${item.name} - ${item.email}"
    }

    override fun getContext(): Context {
        return this
    }
}

// MVP의 장점
// 1. MVC처럼 데이터를 제어하는 코드가 Activity/Fragment 클래스 안에 없어서 간결하고 어디서든 재사용 가능함
// 2. Activity를 view 역할로만 사용하기에 보다 명확하게 역할구분이 됨
// 3. model 역할을 하는 클래스 안에 어떤 view 참조변수도 없기에 완전 분리가 가능함.
// 4. view 역할 클래스 안에 model 참조변수가 없기에 model의 기능이 변경되어도 영향이 없음
// 5. Presenter 클래스가 참조하는 것을 특정 Activity나 Fragment로 정하지 않고 View 인터페이스로 정하였기에 변경이 용이함


// MVP의 단점
// 1. interface를 만들고 class를 구현하여야 하기에 기본적으로 파일들이 많아져서 복잡해보임. 처음 학습과정이 어려움
// 2. view와 presenter가 1:1로 대응되어 있어서 View 역할 클래스가 많아지면 Presenter 파일도 많아짐
// 3. 규모가 커지면 결국 presenter가 할일이 많아져서 비대해짐





















