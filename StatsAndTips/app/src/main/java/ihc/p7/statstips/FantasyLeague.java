package ihc.p7.statstips;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FantasyLeague#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FantasyLeague extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FantasyLeague() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FantasyLeague.
     */
    // TODO: Rename and change types and number of parameters
    public static FantasyLeague newInstance(String param1, String param2) {
        FantasyLeague fragment = new FantasyLeague();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fantasy_league, container, false);
        HandlerDB db = new HandlerDB();
        TextView text = (TextView) v.findViewById(R.id.searchEditText);
        Button btnSearch = (Button) v.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    Fragment frag = new Club();
                    try {
                        Bundle b = new Bundle();
                        b.putString("id_clube", db.getClubePage(text.getText().toString()));
                        frag.setArguments(b);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    getFragmentManager().beginTransaction().replace(R.id.fl_navbar, frag).commit();
                }
            }
        });

        return v;
    }
}