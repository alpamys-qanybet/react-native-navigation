package com.reactnativenavigation.screens;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import androidx.annotation.Nullable; // import android.support.annotation.Nullable;;
import androidx.appcompat.app.AppCompatActivity; // import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.reactnativenavigation.R;
import com.reactnativenavigation.params.ScreenParams;
import com.reactnativenavigation.utils.ViewUtils;
import com.reactnativenavigation.views.ContentView;
import com.reactnativenavigation.views.LeftButtonOnClickListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

@SuppressWarnings("ResourceType")
public class FragmentScreen extends Screen {

    private static final String CONTRACT_GET_FRAGMENT = "getFragment";
    private static final String CONTRACT_GET_SUPPORT_FRAGMENT = "getSupportFragment";
    private FrameLayout content;
    private ContentView contentView;

    public FragmentScreen(AppCompatActivity activity, ScreenParams screenParams, LeftButtonOnClickListener leftButtonOnClickListener) {
        super(activity, screenParams, leftButtonOnClickListener);
    }

    @Override
    public ContentView getContentView() {
        return contentView;
    }

    @Override
    protected void createContent() {
        content = new FrameLayout(getContext());
        content.setId(R.id.fragment_screen_content);
        addContent();
        addFragment();
    }

    private void addContent() {
        contentView = new ContentView(getContext(),
                screenParams.screenId,
                screenParams.navigationParams);
        addView(contentView, 0, 0);
        LayoutParams params = new LayoutParams(MATCH_PARENT, MATCH_PARENT);
        if (screenParams.styleParams.drawScreenBelowTopBar) {
            params.addRule(BELOW, topBar.getId());
        }
        addView(content, params);
    }


    private void addFragment() {
        try {
            Fragment fragment = tryGetFragment();
            if (fragment != null) {
                addFragment(fragment);
                return;
            }

            androidx.fragment.app.Fragment supportFragment = tryGetSupportFragment();
            if (supportFragment != null) {
                addSupportFragment(supportFragment);
                return;
            }
            throw new RuntimeException("must provide public static method " + CONTRACT_GET_FRAGMENT + " or " + CONTRACT_GET_SUPPORT_FRAGMENT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fm = activity.getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.fragment_screen_content, fragment);
        transaction.commitAllowingStateLoss();
    }

    private void addSupportFragment(androidx.fragment.app.Fragment supportFragment) {
        androidx.fragment.app.FragmentManager fm = activity.getSupportFragmentManager();
        androidx.fragment.app.FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.fragment_screen_content, supportFragment);
        transaction.commitAllowingStateLoss();
    }

    @Nullable
    private Fragment tryGetFragment() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException {
        try {
            String className = screenParams.fragmentCreatorClassName;
            Class<?> fragmentCreatorClass = Class.forName(className);
            Method method = fragmentCreatorClass.getMethod(CONTRACT_GET_FRAGMENT, Bundle.class);
            return (android.app.Fragment) method.invoke(null, screenParams.fragmentCreatorPassProps);
        } catch (NoSuchMethodException noSuchMethod) {
            return null;
        }
    }

    @Nullable
    private androidx.fragment.app.Fragment tryGetSupportFragment() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException {
        try {
            String className = screenParams.fragmentCreatorClassName;
            Class<?> fragmentCreatorClass = Class.forName(className);
            Method method = fragmentCreatorClass.getMethod(CONTRACT_GET_SUPPORT_FRAGMENT, Bundle.class);
            return (androidx.fragment.app.Fragment) method.invoke(null, screenParams.fragmentCreatorPassProps);
        } catch (NoSuchMethodException noSuchMethod) {
            return null;
        }
    }

    @Override
    public void unmountReactView() {
        contentView.unmountReactView();
    }

    @Override
    public String getNavigatorEventId() {
        return screenParams.getNavigatorEventId();
    }

    @Override
    public void setOnDisplayListener(final OnDisplayListener onContentViewDisplayedListener) {
        ViewUtils.runOnPreDraw(content, new Runnable() {
            @Override
            public void run() {
                onContentViewDisplayedListener.onDisplay();
            }
        });
    }
}
