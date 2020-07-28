package com.example.viewpager2kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "How to prevent COVID19",
                "Explain how to prevent COVID19",
                R.drawable.facemask
            ),
            IntroSlide(
                "Use Mask",
                "You must wear a mask when you go out",
                R.drawable.covid
            ),
            IntroSlide(
                "Disinfection",
                "Disinfection is mandatory!",
                R.drawable.disinfection
            ),
            IntroSlide(
                "Cover Cough",
                "When you cough, you should cover it",
                R.drawable.cough
            ),
            IntroSlide(
                "Don't touch your eyes",
                "Don't touch your eyes before washing your hands",
                R.drawable.coronavirus
            ),
            IntroSlide(
                "Washing Hands",
                "Washing your hands for 20seconds",
                R.drawable.washinghands
            ),
            IntroSlide(
                "No Flight",
            "Please refrain from traveling for a while",
                R.drawable.noflight
            ),
            IntroSlide(
                "Social Distancing",
                "Practice social distance by maintaining a distance of 2m.",
                R.drawable.socialdistancing
            ),
            IntroSlide(
                "No COVID19",
                "we can overcome COVID19",
                R.drawable.person
            )
        )
    )
    private fun btnTextChange(idx:Int){
        if(idx + 1 < introSliderAdapter.itemCount) {
            buttonNext.text = "NEXT"
        }
        else if(idx + 1 == introSliderAdapter.itemCount){
            buttonNext.text = "START"
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        introSliderViewPager.adapter = introSliderAdapter
        setupIndicators()
        setCurrentIndicator(0)
        introSliderViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
                btnTextChange(position)
            }
        })
        buttonNext.setOnClickListener {

            if(introSliderViewPager.currentItem + 1 < introSliderAdapter.itemCount) {


                if (introSliderViewPager.currentItem + 2 == introSliderAdapter.itemCount) {

                    Log.d(
                        "TEST4!!!",
                        "${introSliderViewPager.currentItem} ()()() ${introSliderAdapter.itemCount}"
                    )
                }
                Log.d(
                    "TEST4!!!",
                    "${introSliderViewPager.currentItem} + ${introSliderAdapter.itemCount}"
                )

                introSliderViewPager.currentItem += 1
            }
            else{
                Log.d("TEST4!!!","${introSliderViewPager.currentItem} ==== ${introSliderAdapter.itemCount}")
                Intent(applicationContext,AnotherActivity::class.java).also{
                    startActivity(it)
                }
            }
        }
        textSkipIntro.setOnClickListener {
            Intent(applicationContext,AnotherActivity::class.java).also{
                startActivity(it)
            }
        }
    }
    private fun setupIndicators(){
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
         layoutParams.setMargins(8,0,8,0)
        for(i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorsContainer.addView(indicators[i])
        }
    }
    private fun setCurrentIndicator(index:Int){
        val childCount = indicatorsContainer.childCount
        for(i in 0 until childCount){
            val imageView = indicatorsContainer[i] as ImageView
            if(i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }

        }
    }
}