package ru.ktn.a4gf.CustomPageTransforms;


import android.view.View;

public class AccordionTransformer extends BaseTransformer {

    @Override
    protected void onTransform(View page, float position) {
        page.setPivotX(position < 0 ? 0 : page.getWidth());
        page.setScaleX(position < 0 ? 1f + position : 1f - position);
    }
}
