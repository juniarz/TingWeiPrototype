package com.ezsofe.tingweiprototype;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onRegisterButtonPressed(View view) {
        EditText username_et = (EditText) getView().findViewById(R.id.register_user_et);
        EditText email_et = (EditText) getView().findViewById(R.id.register_email_et);
        EditText password_et = (EditText) getView().findViewById(R.id.register_password_et);

        ParseUser newUser = new ParseUser();
        newUser.setUsername(username_et.getText().toString());
        newUser.setPassword(password_et.getText().toString());
        newUser.setEmail(email_et.getText().toString());

        try {
            newUser.signUp();

            if (mListener != null) {
                mListener.onRegister(true);
            }
        } catch (ParseException e) {
            Toast.makeText(getActivity(), "Failed to signup user! " + e.getMessage(), Toast.LENGTH_LONG);

            if (mListener != null) {
                mListener.onRegister(false);
            }
        }

    }

    public void onLoginButtonPressed(View view) {
        EditText username_et = (EditText) getView().findViewById(R.id.login_user_et);
        EditText password_et = (EditText) getView().findViewById(R.id.login_password_et);

        try {
            if (ParseUser.logIn(username_et.getText().toString(), password_et.getText().toString()) != null) {
                if (mListener != null) {
                    mListener.onLogin(true);
                }
            }
        } catch (ParseException e) {
            Toast.makeText(getActivity(), "Failed to login user! " + e.getMessage(), Toast.LENGTH_LONG);

            if (mListener != null) {
                mListener.onLogin(false);
            }
        }
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onLoginButtonPressed(View view);

        void onRegisterButtonPressed(View view);

        void onLogin(boolean passed);

        void onRegister(boolean passed);
    }
}
