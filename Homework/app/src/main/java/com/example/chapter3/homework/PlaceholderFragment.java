package com.example.chapter3.homework;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class PlaceholderFragment extends Fragment {
    private ArrayAdapter<Item> adapterItems;
    private ListView lvItems;
    private AnimatorSet animatorSet;
    private LottieAnimationView loadingview;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Item> items = Item.getItems();
        adapterItems = new ArrayAdapter<Item>(getActivity(),
                android.R.layout.simple_list_item_activated_1, items);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);
        lvItems = (ListView) view.findViewById(R.id.lvItems);
        lvItems.setAdapter(adapterItems);
        lvItems.setVisibility(View.GONE);
        loadingview = (LottieAnimationView) view.findViewById(R.id.loading_view);
        loadingview.setAlpha(1f);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                ObjectAnimator animator = ObjectAnimator.ofFloat(loadingview,"alpha", 1f, 0f);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        loadingview.setVisibility(View.GONE);
                    }
                });
                lvItems.setAlpha(0f);
                lvItems.setVisibility(View.VISIBLE);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(lvItems,"alpha",0f,1f);
                animatorSet = new AnimatorSet();
                animatorSet.playTogether(animator,animator1);
                animatorSet.start();
//                ObjectAnimator animator2x = ObjectAnimator.ofFloat(target,"scaleX", 1f,2f);
//                animator2x.setRepeatCount(ObjectAnimator.INFINITE);
//                animator2x.setDuration(Integer.parseInt(durationSelector.getText().toString()));
//                animator2x.setRepeatMode(ObjectAnimator.REVERSE);
            }
        }, 5000);
    }
}
