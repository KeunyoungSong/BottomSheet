// BottomSheetDialog.kt
package com.opensource.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.opensource.bottomsheet.databinding.FragmentBottomSheetDialogBinding

class BottomSheetDialog(
	val test: Int = 3
) : BottomSheetDialogFragment() {
	
	private var _binding: FragmentBottomSheetDialogBinding? = null
	private val binding get() = _binding!!
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentBottomSheetDialogBinding.inflate(inflater, container, false)
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		// 부모 뷰에 동적으로 뷰를 추가하고 고정
		binding.root.viewTreeObserver.addOnGlobalLayoutListener {
			// design_bottom_sheet FrameLayout을 찾아서 그 부모인 CoordinatorLayout을 찾음
			val bottomSheet = dialog?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
			val coordinatorLayout = bottomSheet?.parent as? CoordinatorLayout
			
			if (coordinatorLayout != null) {
				// 새 뷰를 생성
				val newView = TextView(context).apply {
					layoutParams = CoordinatorLayout.LayoutParams(
						CoordinatorLayout.LayoutParams.MATCH_PARENT,
						200 // 높이 400dp
					).apply {
						gravity = android.view.Gravity.BOTTOM
					}
					setBackgroundColor(resources.getColor(android.R.color.holo_red_dark, null)) // 배경색 빨간색
				}
				
				// CoordinatorLayout에 새 뷰 추가
				coordinatorLayout.addView(newView)
				// 뷰를 맨 앞에 배치
				ViewCompat.setElevation(newView, 100f)
				
				// 뷰의 위치 조정 필요 없음, gravity 속성이 TOP이므로 위쪽에 고정됨
			}
			
			// Listener 제거
//			binding.root.viewTreeObserver.removeOnGlobalLayoutListener(this)
		}
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}