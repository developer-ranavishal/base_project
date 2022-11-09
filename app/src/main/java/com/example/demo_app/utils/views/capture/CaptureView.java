package com.example.demo_app.utils.views.capture;//package app.beYou.utils.views.capture;
//
//import android.animation.Animator;
//import android.animation.AnimatorListenerAdapter;
//import android.animation.AnimatorSet;
//import android.animation.ObjectAnimator;
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.graphics.drawable.Animatable;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.animation.LinearInterpolator;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.core.content.ContextCompat;
//
//import app.beYou.R;
//
//
//public class CaptureView extends ConstraintLayout {
//
//    Context context = null;
//
//    ConstraintLayout clPostView;
//    ImageView ivButtonPostGallery;
//
//    ConstraintLayout clStoryView;
//    ImageView ivButtonStoryGallery;
//    //ImageView ivButtonStory;
//    ImageView ivButtonStoryEffects;
//
//    ConstraintLayout clVideoView;
//    ImageView ivButtonVideoGallery;
//    ImageView ivButtonVideoAnim;
//    ImageView ivButtonVideoEffects;
//
//    ConstraintLayout clLiveView;
//    ImageView ivButtonLiveAnim;
//
//    ConstraintLayout clEffectsView;
//    ImageView ivEffectsClose;
//    //ImageView ivShootWithEffects;
//   // ViewPager2 rvEffects;
//
//    ImageView ivCaptureButton;
//    ImageView ivCaptureButtonCentrePart;
//
//    OnCaptureEvents onCaptureEvents = null;
//    Boolean isRecording = false;
//    Boolean isLongPressed = false;
//    Boolean isTimer = false;
//
//    public CaptureView(@NonNull Context context) {
//        super(context);
//        this.context = context;
//        init(context);
//    }
//
//    public CaptureView(@NonNull Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        this.context = context;
//        init(context);
//    }
//
//    public CaptureView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        this.context = context;
//        init(context);
//    }
//
//    public CaptureView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        this.context = context;
//        init(context);
//    }
//
//    private void init(Context context) {
//        inflate(context, R.layout.argear_capture_view, this);
//        assignViews();
//        setUpAnimatorSet();
//        initListeners();
//    }
//
////    public void setAdapter(ArrayList<ItemModel> items){
////        rvEffects.setAdapter(new EffectsAdapter(items, rvEffects, new EffectsAdapter.OnItemClick() {
////            @Override
////            public void onEffectClick(int position, @NotNull ItemModel item) {
////                /*if (onCaptureEvents != null) {
////                    onCaptureEvents.onEffectSelected(position,item);
////                }*/
////            }
////        }));
////        rvEffects.smoothScrollToPosition(0);
////    }
//
//    private void assignViews() {
//        clPostView = findViewById(R.id.clPostView);
//        ivButtonPostGallery = findViewById(R.id.ivButtonPostGallery);
//
//        clStoryView = findViewById(R.id.clStoryView);
//        ivButtonStoryGallery = findViewById(R.id.ivButtonStoryGallery);
//        //ivButtonStory = findViewById(R.id.ivButtonStory);
//        ivButtonStoryEffects = findViewById(R.id.ivButtonStoryEffects);
//
//        clVideoView = findViewById(R.id.clVideoView);
//        ivButtonVideoGallery = findViewById(R.id.ivButtonVideoGallery);
//        ivButtonVideoAnim = findViewById(R.id.ivButtonVideoAnim);
//        ivButtonVideoEffects = findViewById(R.id.ivButtonVideoEffects);
//
//        clLiveView = findViewById(R.id.clLiveView);
//        ivButtonLiveAnim = findViewById(R.id.ivButtonLiveAnim);
//
//        clEffectsView = findViewById(R.id.clEffectsView);
//        ivEffectsClose = findViewById(R.id.ivEffectsClose);
////        rvEffects = findViewById(R.id.rvFilters);
//
//        ivCaptureButton = findViewById(R.id.ivCaptureButton);
//        ivCaptureButtonCentrePart = findViewById(R.id.ivCaptureButtonCentrePart);
//
//        PickerLayoutManager pickerLayoutManager = new PickerLayoutManager(context, PickerLayoutManager.HORIZONTAL, false);
//        pickerLayoutManager.setChangeAlpha(false);
//
//        pickerLayoutManager.setOnScrollStopListener(new PickerLayoutManager.onScrollStopListener(){
//            @Override
//            public void selectedView(View view, int selected) {
//                if (onCaptureEvents != null) {
//                  //  int layoutPosition = rvEffects.getChildLayoutPosition(view);
////                    onCaptureEvents.onEffectSelected(layoutPosition);
////                    if(layoutPosition == 0 && closeEffects){
////                        closeEffects = false;
////                        final Handler handler = new Handler();
////                        handler.postDelayed(new Runnable() {
////                            @Override
////                            public void run() {
////                                setViews();
////                            }
////                        }, 200);
////                    }
//                }
//            }
//        });
//
////        LinearSnapHelper snapHelper = new LinearSnapHelper();
////        snapHelper.attachToRecyclerView(rvEffects);
////
////        rvEffects.setLayoutManager(pickerLayoutManager);
////        rvEffects.setAdapter(new EffectsAdapter(new ArrayList<ItemModel>(),rvEffects,new EffectsAdapter.OnItemClick() {
////            @Override
////            public void onEffectClick(int position, @NotNull ItemModel item) {
////
////            }
////        }));
////        rvEffects.setLayoutManager(new CenterZoomLayoutManager(context, RecyclerView.HORIZONTAL, false));
//    }
//
//    @SuppressLint("ClickableViewAccessibility")
//    private void initListeners() {
//        /**For Post*/
//        ivButtonPostGallery.setOnClickListener(new OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                if (onCaptureEvents != null) {
//                    onCaptureEvents.onSideGalleryClick();
//                }
//            }
//        });
//        /**For Story*/
//        ivButtonStoryGallery.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onCaptureEvents != null) {
//                    onCaptureEvents.onBottomGalleryClick();
//                }
//            }
//        });
//        ivButtonStoryEffects.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showEffects();
//                onCaptureEvents.onBottomEffectsClick();
//            }
//        });
//
//        /**For Video*/
//        ivButtonVideoGallery.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onCaptureEvents != null) {
//                    //showEffects();
//                    onCaptureEvents.onBottomGalleryClick();
//                }
//            }
//        });
//        ivButtonVideoEffects.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showEffects();
//                onCaptureEvents.onBottomEffectsClick();
//            }
//        });
//        /**For Live*/
//        ivEffectsClose.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onCaptureEvents != null) {
//                    onCaptureEvents.onEffectClose();
//                }
//                setViews();
//            }
//        });
//        ivCaptureButton.setOnClickListener(new OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                //Todo: Click and Long Pressed Gallery
//                if(onCaptureEvents != null){
//                        if(cameraUseCaseType == CameraUseCaseType.POST){
//                        onCaptureEvents.onCapturePhotoClick();
//                    }
//                    if(cameraUseCaseType == CameraUseCaseType.STORY){
//                        onCaptureEvents.onCapturePhotoClick();
//                    }
//                    if(cameraUseCaseType == CameraUseCaseType.VIDEO){
//                        if(!isRecording){
//                            isRecording = true;
//                            VideoConstant.isStopRecord = false;
//                            onCaptureEvents.onRecordStartClick();
//                        }else{
//                            isRecording = false;
//                            VideoConstant.isStopRecord = true;
//                            onCaptureEvents.onRecordStopClick();
//                        }
//                    }
//                    if(cameraUseCaseType == CameraUseCaseType.LIVE){
//                        onCaptureEvents.onLiveClick();
//                    }
//                }
//            }
//        });
//        ivCaptureButton.setOnLongClickListener(new OnLongClickListener(){
//            @Override
//            public boolean onLongClick(View v) {
//                if(cameraUseCaseType == CameraUseCaseType.STORY && onCaptureEvents != null){
//                    onCaptureEvents.onRecordStartClick();
//                    VideoConstant.isStopRecord = false;
//                    isRecording = true;
//                    isLongPressed = true;
//                }
//                return true;
//            }
//        });
//        ivCaptureButton.setOnTouchListener((v, event) -> {
//            v.onTouchEvent(event);
//            if(cameraUseCaseType == CameraUseCaseType.STORY && onCaptureEvents != null){
//                if(event.getAction() == MotionEvent.ACTION_UP){
//                    if(isLongPressed){
//                        Log.d("CaptureView", "initListeners: Recording Ended");
//                        onCaptureEvents.onRecordStopClick();
//                        isLongPressed = false;
//                        isRecording = false;
//                        VideoConstant.isStopRecord = true;
//                        //return false;
//                    }
//                }
//            }
//            return true;
//        });
//    }
//
//    public void setCameraUseCaseType(CameraUseCaseType camerauseCaseType) {
//        this.cameraUseCaseType = camerauseCaseType;
//        setViews();
//    }
//
//    public void setViews() {
//        if (cameraUseCaseType == CameraUseCaseType.POST) {
//            clPostView.setVisibility(View.VISIBLE);
//            clStoryView.setVisibility(View.GONE);
//            clVideoView.setVisibility(View.GONE);
//            clLiveView.setVisibility(View.GONE);
//            clEffectsView.setVisibility(View.GONE);
//        }
//
//        if (cameraUseCaseType == CameraUseCaseType.STORY) {
//            clPostView.setVisibility(View.GONE);
//            clStoryView.setVisibility(View.VISIBLE);
//            clVideoView.setVisibility(View.GONE);
//            clLiveView.setVisibility(View.GONE);
//            clEffectsView.setVisibility(View.GONE);
//            clStoryView.setAlpha(1.0f);
//        }
//
//        if (cameraUseCaseType == CameraUseCaseType.VIDEO) {
//            clPostView.setVisibility(View.GONE);
//            clStoryView.setVisibility(View.GONE);
//            clVideoView.setVisibility(View.VISIBLE);
//            clVideoView.setAlpha(1.0f);
//            clLiveView.setVisibility(View.GONE);
//            clEffectsView.setVisibility(View.GONE);
//            ivButtonVideoGallery.setVisibility(View.VISIBLE);
//            ivButtonVideoGallery.setAlpha(1.0f);
//            ivButtonVideoEffects.setVisibility(View.VISIBLE);
//            ivButtonVideoEffects.setAlpha(1.0f);
//            Animatable animatable = (Animatable) ivButtonVideoAnim.getDrawable();
//            animatable.start();
//        }
//
//        if (cameraUseCaseType == CameraUseCaseType.LIVE) {
//            clPostView.setVisibility(View.GONE);
//            clStoryView.setVisibility(View.GONE);
//            clVideoView.setVisibility(View.GONE);
//            clLiveView.setVisibility(View.VISIBLE);
//            clEffectsView.setVisibility(View.GONE);
//            Animatable animatable = (Animatable) ivButtonLiveAnim.getDrawable();
//            animatable.start();
//        }
//    }
//
//    public void showEffects() {
//        clPostView.setVisibility(View.GONE);
//        clStoryView.setVisibility(View.GONE);
//        clVideoView.setVisibility(View.GONE);
//        clLiveView.setVisibility(View.GONE);
//        clEffectsView.setVisibility(View.VISIBLE);
//    }
//
//    public void setCaptureEvents(OnCaptureEvents onCaptureEvents) {
//        this.onCaptureEvents = onCaptureEvents;
//    }
//
//    AnimatorSet animatorSet = new AnimatorSet();
//
//    public void startRecordingVideoView() {
//        isRecording = true;
//        ivCaptureButton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.argear_svg_capture_outer));
//        animatorSet.start();
//        shouldReallyStop =  false;
//        /*AnimationDrawable animDraw = (AnimationDrawable) ivCaptureButton.getDrawable();
//        animDraw.start();*/
//
//        if (cameraUseCaseType == CameraUseCaseType.VIDEO) {
//            hideView(clVideoView);
//        }
//        if (cameraUseCaseType == CameraUseCaseType.STORY) {
//            hideView(clStoryView);
//        }
//        clEffectsView.setVisibility(View.GONE);
//        ivCaptureButtonCentrePart.setVisibility(View.VISIBLE);
//    }
//
//    public void stopRecordingVideoView() {
//        isRecording = false;
//        shouldReallyStop = true;
//        clEffectsView.setVisibility(View.GONE);
//        animatorSet.end();
//        ivCaptureButton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.argear_record_button_post));
//        ivCaptureButtonCentrePart.setVisibility( View.GONE);
//        setViews();
//    }
//
//    Boolean shouldReallyStop  = false;
//
//    public void setUpAnimatorSet(){
//        ObjectAnimator objAnimExpandX = ObjectAnimator.ofFloat(ivCaptureButton, "scaleX", 1.0f, 1.3f);
//        ObjectAnimator objAnimExpandY = ObjectAnimator.ofFloat(ivCaptureButton, "scaleY", 1.0f, 1.3f);
//
//        ObjectAnimator objAnimCollapseX = ObjectAnimator.ofFloat(ivCaptureButton, "scaleX", 1.3f, 1.0f);
//        ObjectAnimator objAnimCollapseY = ObjectAnimator.ofFloat(ivCaptureButton, "scaleY", 1.3f, 1.0f);
//
//        AnimatorSet animatorSetExpand = new AnimatorSet();
//        animatorSetExpand.setDuration(1000);
//        animatorSetExpand.playTogether(objAnimExpandX,objAnimExpandY);
//        animatorSetExpand.setInterpolator(new LinearInterpolator());
//
//        AnimatorSet animatorSetCollapse = new AnimatorSet();
//        animatorSetCollapse.setDuration(1000);
//        animatorSetCollapse.playTogether(objAnimCollapseX,objAnimCollapseY);
//        animatorSetCollapse.setInterpolator(new LinearInterpolator());
//
//        animatorSet.playSequentially(animatorSetExpand, animatorSetCollapse);
//        animatorSet.addListener(new AnimatorListenerAdapter(){
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                if(!shouldReallyStop){
//                    animatorSet.start();
//                }
//            }
//        });
//    }
//
//    public void hideView(ConstraintLayout cl){
//        ObjectAnimator objAnimFirst = ObjectAnimator.ofFloat(cl, "alpha", 1.0f, 0.0f);
//        objAnimFirst.setDuration(300);
//        objAnimFirst.start();
//        objAnimFirst.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                cl.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//    }
//
//    public void showView(ConstraintLayout cl){
//        cl.setVisibility(View.VISIBLE);
//        ObjectAnimator objAnimFirst = ObjectAnimator.ofFloat(cl, "alpha", 0.0f, 1.0f);
//        objAnimFirst.setDuration(300);
//        objAnimFirst.start();
//    }
//
//    public interface OnCaptureEvents {
//        void onSideGalleryClick();
//        void onBottomGalleryClick();
//        void onBottomEffectsClick();
//
//        void onCapturePhotoClick();
//
//        void onRecordStartClick();
//        void onRecordStopClick();
//
//        void onEffectClose();
//        void onEffectSelected(int position);
//
//        void onLiveClick();
//    }
//}