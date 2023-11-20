package com.example.healthapp01.Fragments

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.healthapp01.R
import com.example.healthapp01.databinding.FragmentStoreBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF

// 스토어 프래그먼트
class StoreFragment : Fragment() {

    lateinit var pieChart: PieChart

    private lateinit var binding: FragmentStoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store, container, false)
        
        // 파이 차트
        pieChart = binding.pieChart

        // 사용자 백분율 값을 설정
        // 설명을 활성화로 설정하고 원형 차트에 대한 오프셋을 설정
        pieChart.setUsePercentValues(true)
        pieChart.getDescription().setEnabled(false)
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)

        // pieChart 드래그 설정
        pieChart.setDragDecelerationFrictionCoef(0.95f)

        // 가운데 원 설정 // 색상 설정
        pieChart.setDrawHoleEnabled(true)
        pieChart.setHoleColor(Color.WHITE)

        // 구분선 설정
        pieChart.setTransparentCircleColor(Color.WHITE)
        pieChart.setTransparentCircleAlpha(110)

        // pieChart 구멍 반경 설정
        pieChart.setHoleRadius(58f)
        pieChart.setTransparentCircleRadius(61f)

        // 센터 텍스트 설정
        pieChart.setDrawCenterText(true)

        // pieChart 회전 설정
        pieChart.setRotationAngle(0f)

        // 터치로 원형차트 회전 활성화
        pieChart.setRotationEnabled(true)
        pieChart.setHighlightPerTapEnabled(true)

        // pieChart 애니메이션 설정
        pieChart.animateY(1400, Easing.EaseInOutQuad)

        // 그래프 설명 사용 여부 true/false
        pieChart.legend.isEnabled = false
        pieChart.setEntryLabelColor(Color.WHITE)
        pieChart.setEntryLabelTextSize(12f)

        // 배열 목록 생성 // 표시할 데이터 추가 << 이부분 동적으로 생성될수 있게 수정
        val entries: ArrayList<PieEntry> = ArrayList()
        entries.add(PieEntry(60f))
        entries.add(PieEntry(20f))
        entries.add(PieEntry(10f))
        entries.add(PieEntry(10f))

        // pie dataSet 설정
        val dataSet = PieDataSet(entries, "Exercise")

        // 아이콘 설정
        //dataSet.setDrawIcons(false)

        // 파이 조각 설정
        dataSet.sliceSpace = 3f //항목간 간격
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // 목록에 색상 추가 // 색상 여러 가지는 어떻게?
        val colors: ArrayList<Int> = ArrayList()
        colors.add(resources.getColor(R.color.purple_200))
        colors.add(resources.getColor(R.color.yellow))
        colors.add(resources.getColor(R.color.red))
        colors.add(resources.getColor(R.color.black))

        // 색상 설정
        dataSet.colors = colors

        // 중앙 텍스트 설정
        pieChart.centerText = "Exercise"

        // 파이 데이터 세트 설정
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(15f)
        data.setValueTypeface(Typeface.DEFAULT_BOLD)
        data.setValueTextColor(Color.WHITE)
        pieChart.setData(data)

        // 모든 하이라이트 실행취소
        pieChart.highlightValues(null)

        // 차트 로딩
        pieChart.invalidate()
        

        // 프래그먼트 전환
        binding.homeTap.setOnClickListener{
            it.findNavController().navigate(R.id.action_storeFragment_to_homeFragment)
        }
        binding.tipTap.setOnClickListener{
            it.findNavController().navigate(R.id.action_storeFragment_to_tipFragment)
        }
        binding.plusTap.setOnClickListener{
            it.findNavController().navigate(R.id.action_storeFragment_to_plusFragment)
        }
        binding.bookmarkTap.setOnClickListener{
            it.findNavController().navigate(R.id.action_storeFragment_to_bookmarkFragment)
        }

        return binding.root

    }

}