package TestNavigationDrawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RelativeLayout;

import com.example.deathstroke.uniqueoff1.R;



public class MenuFragment extends Fragment implements View.OnTouchListener {

    GestureDetector gestureDetector;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.slider_menu, container, false);
        RelativeLayout root =  rootView.findViewById(R.id.rootLayout);
        /*gestureDetector=new GestureDetector(getActivity(),new OnSwipeListener(){

            @Override
            public boolean onSwipe(Direction direction) {
                if (direction==Direction.up){
                    //do your stuff
                    ((MainActivity )  getActivity()).hideFragment();

                }

                if (direction==Direction.down){
                    //do your stuff

                }
                return true;
            }


        });
        root.setOnTouchListener(this);*/
        return rootView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }
}
