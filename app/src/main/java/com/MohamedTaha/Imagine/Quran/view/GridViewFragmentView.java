package com.MohamedTaha.Imagine.Quran.view;

import com.MohamedTaha.Imagine.Quran.model.ModelSora;

import java.util.List;

public interface GridViewFragmentView {
    void showAfterSearch();
    void showAfterQueryText(List<ModelSora> stringList);
    void hideProgress();
    void showProgress();
    void isEmpty();
    void thereData();
    void showAllINameSour(List<ModelSora> strings);
    void showAllImages(List<Integer> integers);
    void showAnimation();

}
