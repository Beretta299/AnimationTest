package com.karas.vector.player.presentation

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.vectordrawable.graphics.drawable.SeekableAnimatedVectorDrawable
import com.karas.vector.Application
import com.karas.vector.R
import com.karas.vector.base.BaseFragment
import com.karas.vector.base.utils.OnSeekBarProgressChanged
import com.karas.vector.base.utils.ZoomGestureListener
import com.karas.vector.base.utils.viewModel
import com.karas.vector.databinding.PlayerFragmentBinding
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class PlayerFragment: BaseFragment<PlayerFragmentBinding>() {

    lateinit var animatedVectorDrawable: SeekableAnimatedVectorDrawable
    private var duration: Long = 0
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    lateinit var viewModel: PlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.appComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = viewModel(viewModelFactory){
        }

        initViews()
        initGestureDetector()
    }

    override fun bind(): PlayerFragmentBinding {
        return PlayerFragmentBinding.inflate(layoutInflater)
    }



    private fun initViews() {
        with(binding) {

            btnPause.setOnClickListener {
                compositeDisposable.clear()
                animatedVectorDrawable.pause()
            }
            btnStart.setOnClickListener {
                if(animatedVectorDrawable.isPaused) {
                    animatedVectorDrawable.resume()
                }
                else animatedVectorDrawable.start()
                customizeSpeedOfAnimation(seekBar.progress)
            }
            ivFirstAnimation.setOnClickListener {
                setAnimationToContainer(R.drawable.animated_android)
            }
            ivSecondAnimation.setOnClickListener {
                setAnimationToContainer(R.drawable.avd_anim)

            }
            ivThirdAnimation.setOnClickListener {
                setAnimationToContainer(R.drawable.animated_star)
            }


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                seekBar.min = 10
                seekBar.max = 200
                seekBar.setOnSeekBarChangeListener(object : OnSeekBarProgressChanged() {
                    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                        customizeSpeedOfAnimation(p1)
                    }
                })
            }

            initColorContainers()

            setAnimationToContainer(com.karas.vector.R.drawable.avd_anim)
            ivMainContainer.setOnTouchListener { v, event ->
                scaleGestureDetector.onTouchEvent(
                    event
                )
            }
        }
    }

    private fun customizeSpeedOfAnimation(animationSpeed: Int) {
        compositeDisposable.clear()
        compositeDisposable.add(Single.timer(10, TimeUnit.MILLISECONDS)
            .repeatUntil {
                return@repeatUntil duration < animatedVectorDrawable.totalDuration
            }
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                duration+=animationSpeed.toLong()
                animatedVectorDrawable.currentPlayTime = (animatedVectorDrawable.totalDuration *
                        (duration.toFloat() / animatedVectorDrawable.totalDuration.toFloat())).toLong()
            }, {
                it.printStackTrace()
            }))
    }

    private fun setAnimationToContainer(drawableID: Int) {
        animatedVectorDrawable = SeekableAnimatedVectorDrawable.create(
            requireContext(),
            drawableID
        )!!

        binding.ivMainContainer.setImageDrawable(animatedVectorDrawable)

        val backgroundColorAnimator = ObjectAnimator.ofInt(
            animatedVectorDrawable,
            "alpha",
            0, 255)
        backgroundColorAnimator.duration = 300
        backgroundColorAnimator.start()

        customizeSpeedOfAnimation(binding.seekBar.progress)
    }

    private fun initColorContainers() {
        for(color in viewModel.getColorsList()) {
            val button = layoutInflater.inflate(R.layout.button, null)
            button.setBackgroundColor(color)
            button.setOnClickListener {
                binding.clMainLayout.setBackgroundColor(color)
            }
            val params = LinearLayout.LayoutParams(300, 300)
            params.setMargins(0, 0, 15, 0)
            button.layoutParams = params
            binding.llColorContainer.addView(button)
        }
    }

    private fun zoomImage(value: Float) {
        val scaleX = ObjectAnimator.ofFloat(binding.ivMainContainer, "scaleX", value)
        val scaleY = ObjectAnimator.ofFloat(binding.ivMainContainer, "scaleY", value)
        scaleX.start()
        scaleY.start()
    }

    private fun initGestureDetector() {
        scaleGestureDetector = ScaleGestureDetector(requireContext(), object :ZoomGestureListener() {
            override fun onScale(detector: ScaleGestureDetector?): Boolean {
                if(detector?.scaleFactor!! > 1) {
                    zoomImage(2f)
                } else {
                    zoomImage(1f)

                }
                return true
            }
        })
    }

}