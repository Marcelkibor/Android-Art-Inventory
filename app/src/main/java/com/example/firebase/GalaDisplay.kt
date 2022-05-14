package com.example.firebase
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidplot.pie.Segment
import com.androidplot.pie.SegmentFormatter
import com.bumptech.glide.Glide
import com.example.firebase.databinding.GaladisplayBinding
import com.google.firebase.auth.FirebaseAuth
private var future:Int? = null
private var currentValue:Int? = null
private var marginal:Int? = null
private var ratesing:Int? = null
private lateinit var binding: GaladisplayBinding
private lateinit var auth:FirebaseAuth
//this is used as the registration activity
class GalaDisplay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GaladisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle: Bundle? = intent.extras
        val image = bundle?.getString("image")
        val name = bundle?.getString("name")
        val ftrVal = bundle?.getString("ftrVal")
        val currVal = bundle?.getString("currVal")
        val margin = bundle?.getString("margin")
        val rate = bundle?.getString("rate")
        future = ftrVal?.toInt()
        currentValue = currVal?.toInt()
        marginal = margin?.toInt()
        ratesing = rate?.toInt()
        val futureValue = Segment("Future", future)
        val currentVal = Segment("Current", currentValue)
        val marginalVal = Segment("Margin", marginal)
        binding.tvMargin.text = margin.toString()
        binding.tvItemName.text = name
        binding.tvcurrent.text = currVal
        binding.tvfutureValue.text = ftrVal
        Glide.with(baseContext).load(image).into(binding.galImage)
//        set pie chart data
        val customChart = binding.pieChart
        val colPrip = SegmentFormatter(Color.YELLOW)
        val colPred = SegmentFormatter(Color.RED)
        val colMarg = SegmentFormatter(Color.BLUE)
        customChart.addSegment(futureValue, colPred)
        customChart.addSegment(currentVal, colPrip)
        customChart.addSegment(marginalVal, colMarg)
      checkScore()
    }
    private fun checkScore() {
        if (marginal!! <50* currentValue!!){
              binding.tvScoreLimit.text = "AVERAGE"
          }
        else if (marginal!! <80* currentValue!!&& marginal!! <10* currentValue!!){
            binding.tvScoreLimit.text = "GOOD"
        }
        else{
            binding.tvScoreLimit.text = "BEST"
        }

        }
}