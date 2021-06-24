package ihc.p7.statstips;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Player#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Player extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    HandlerDB db = null;

    public Player() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Player.
     */
    // TODO: Rename and change types and number of parameters
    public static Player newInstance(String param1, String param2) {
        Player fragment = new Player();
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
        View v = inflater.inflate(R.layout.fragment_player, container, false);

        String[] value = getArguments() != null ? String.valueOf(getArguments().getString("player")).split(";") : null;

        db = new HandlerDB();

        System.err.println("Player() -> onCreateView()");
        System.err.println(Arrays.toString(value));

        ImageView goBack = (ImageView) v.findViewById(R.id.goBack);

        TextView textViewGRVal = (TextView) v.findViewById(R.id.textViewGRVal);
        TextView textViewDEFVal1 = (TextView) v.findViewById(R.id.textViewDEFVal1);
        TextView textViewDEFVal2 = (TextView) v.findViewById(R.id.textViewDEFVal2);
        TextView textViewDEFVal3 = (TextView) v.findViewById(R.id.textViewDEFVal3);
        TextView textViewDEFVal4 = (TextView) v.findViewById(R.id.textViewDEFVal4);
        TextView textViewMEDVal1 = (TextView) v.findViewById(R.id.textViewMEDVal1);
        TextView textViewMEDVal2 = (TextView) v.findViewById(R.id.textViewMEDVal2);
        TextView textViewMEDVal3 = (TextView) v.findViewById(R.id.textViewMEDVal3);
        TextView textViewMEDVal4 = (TextView) v.findViewById(R.id.textViewMEDVal4);
        TextView textViewAVVal = (TextView) v.findViewById(R.id.textViewAVVal);
        TextView textViewAVVal2 = (TextView) v.findViewById(R.id.textViewAVVal2);

        Map<String, String> id_jogadores = new HashMap<>();

        int def = 0, med = 0, av = 0;
        String id_clube = null;
        for (int i = 0; i < Objects.requireNonNull(value).length - 4; i+=4){
            //i:nome, i+1:posicao, i+2:id_jogador, i+3:id_clube
            value[i] = value[i].trim();
            value[i+1] = value[i+1].trim();
            value[i+2] = value[i+2].trim();
            id_clube = value[i+3].trim();

            switch (value[i+1].trim()){
                case "GR":
                    textViewGRVal.setText(value[i].trim());
                    id_jogadores.put("GR", value[i+2]);
                    break;
                case "DEF":
                    switch (def){
                        case 0:
                            textViewDEFVal1.setText(value[i].trim());
                            id_jogadores.put("DEF1", value[i+2]);
                            def++;
                            break;
                        case 1:
                            textViewDEFVal2.setText(value[i].trim());
                            id_jogadores.put("DEF2", value[i+2]);
                            def++;
                            break;
                        case 2:
                            textViewDEFVal3.setText(value[i].trim());
                            id_jogadores.put("DEF3", value[i+2]);
                            def++;
                            break;
                        case 3:
                            textViewDEFVal4.setText(value[i].trim());
                            id_jogadores.put("DEF4", value[i+2]);
                            def++;
                            break;
                        default:
                            System.err.println("Erro: fill [DEF] Player()");
                    }
                    break;
                case "MED":
                    switch (med) {
                        case 0:
                            textViewMEDVal1.setText(value[i].trim());
                            id_jogadores.put("MED1", value[i+2]);
                            med++;
                            break;
                        case 1:
                            textViewMEDVal2.setText(value[i].trim());
                            id_jogadores.put("MED2", value[i+2]);
                            med++;
                            break;
                        case 2:
                            textViewMEDVal3.setText(value[i].trim());
                            id_jogadores.put("MED3", value[i+2]);
                            med++;
                            break;
                        case 3:
                            textViewMEDVal4.setText(value[i].trim());
                            id_jogadores.put("MED4", value[i+2]);
                            med++;
                            break;
                        default:
                            System.err.println("Erro: fill [MED] Player()");
                    }
                    break;
                case "AV":
                    switch (av) {
                        case 0:
                            textViewAVVal.setText(value[i].trim());
                            id_jogadores.put("AV1", value[i+2]);
                            av++;
                            break;
                        case 1:
                            textViewAVVal2.setText(value[i].trim());
                            id_jogadores.put("AV2", value[i+2]);
                            av++;
                            break;
                        default:
                            System.err.println("Erro: fill [AV]] Player()");
                    }
                    break;
                default:
                    System.err.printf("Erro: fill [pos=%s] Player()\n", value[i+1]);
            }

        }

        // GO BACK button
        String finalId_clube = id_clube;
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    Fragment frag = new Club();
                    System.err.printf("ID CLUB=%s\n", finalId_clube);
                    // SQL Query
                    try {
                        Bundle b = new Bundle();
                        b.putString("club", db.getClubeByID(finalId_clube));
                        frag.setArguments(b);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    getFragmentManager().beginTransaction().replace(R.id.fl_navbar, frag).commit();
                }
            }
        });

        textViewGRVal.setOnClickListener(v1 -> {
            if (getFragmentManager() != null) {
                getFragmentManager().beginTransaction().replace(R.id.fl_navbar, getPlayerPageWithQuery(id_jogadores.get("GR"))).commit();
            }
        });

        textViewDEFVal1.setOnClickListener(v1 -> {
            if (getFragmentManager() != null) {
                getFragmentManager().beginTransaction().replace(R.id.fl_navbar, getPlayerPageWithQuery(id_jogadores.get("DEF1"))).commit();
            }
        });

        textViewDEFVal2.setOnClickListener(v1 -> {
            if (getFragmentManager() != null) {
                getFragmentManager().beginTransaction().replace(R.id.fl_navbar, getPlayerPageWithQuery(id_jogadores.get("DEF2"))).commit();
            }
        });

        textViewDEFVal3.setOnClickListener(v1 -> {
            if (getFragmentManager() != null) {
                getFragmentManager().beginTransaction().replace(R.id.fl_navbar, getPlayerPageWithQuery(id_jogadores.get("DEF3"))).commit();
            }
        });

        textViewDEFVal4.setOnClickListener(v1 -> {
            if (getFragmentManager() != null) {
                getFragmentManager().beginTransaction().replace(R.id.fl_navbar, getPlayerPageWithQuery(id_jogadores.get("DEF4"))).commit();
            }
        });

        textViewMEDVal1.setOnClickListener(v1 -> {
            if (getFragmentManager() != null) {
                getFragmentManager().beginTransaction().replace(R.id.fl_navbar, getPlayerPageWithQuery(id_jogadores.get("MED1"))).commit();
            }
        });

        textViewMEDVal2.setOnClickListener(v1 -> {
            if (getFragmentManager() != null) {
                getFragmentManager().beginTransaction().replace(R.id.fl_navbar, getPlayerPageWithQuery(id_jogadores.get("MED2"))).commit();
            }
        });

        textViewMEDVal3.setOnClickListener(v1 -> {
            if (getFragmentManager() != null) {
                getFragmentManager().beginTransaction().replace(R.id.fl_navbar, getPlayerPageWithQuery(id_jogadores.get("MED3"))).commit();
            }
        });

        textViewMEDVal4.setOnClickListener(v1 -> {
            if (getFragmentManager() != null) {
                getFragmentManager().beginTransaction().replace(R.id.fl_navbar, getPlayerPageWithQuery(id_jogadores.get("MED4"))).commit();
            }
        });

        textViewAVVal.setOnClickListener(v1 -> {
            if (getFragmentManager() != null) {
                getFragmentManager().beginTransaction().replace(R.id.fl_navbar, getPlayerPageWithQuery(id_jogadores.get("AV1"))).commit();
            }
        });

        textViewAVVal2.setOnClickListener(v1 -> {
            if (getFragmentManager() != null) {
                getFragmentManager().beginTransaction().replace(R.id.fl_navbar, getPlayerPageWithQuery(id_jogadores.get("AV2"))).commit();
            }
        });

        return v;
    }

    public Fragment getPlayerPageWithQuery(String id_jogador){
        Fragment frag = new PlayerPage();

        // SQL Query
        try {
            Bundle b = new Bundle();
            b.putString("player_page", db.getPlayerPage(id_jogador));
            frag.setArguments(b);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return frag;
    }
}