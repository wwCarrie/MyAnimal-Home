package com.example.animalhome.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalhome.R;
import com.example.animalhome.activity.AnimalKnowledgeActivity;
import com.example.animalhome.activity.AnimalSpeciesActivity;
import com.example.animalhome.adapter.AnimalKnowledgeAdapter;
import com.example.animalhome.adapter.AnimalSpeciesAdapter;
import com.example.animalhome.entity.AnimalKnowledge;
import com.example.animalhome.entity.AnimalSpecies;
import com.example.animalhome.utils.AppDataUtils;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private TextView tvSearch, tvName, tvDesc, tvDesc2;

    private ImageView ivAnimal;

    private RecyclerView rvAnimalSpecies, rvAnimalKnowledge;

    private List<AnimalSpecies> animalSpeciesList;

    private AnimalSpeciesAdapter animalSpeciesAdapter;

    private AnimalKnowledgeAdapter animalKnowledgeAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = getLayoutInflater().inflate(R.layout.fragment_home, container, false);
        tvSearch = inflate.findViewById(R.id.tv_search);
        rvAnimalSpecies = inflate.findViewById(R.id.rv_animal_species);
        rvAnimalKnowledge = inflate.findViewById(R.id.rv_animal_knowledge);
        initDate();
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initDate() {
        animalSpeciesAdapter = new AnimalSpeciesAdapter();
        animalSpeciesAdapter.setOnItemClickListener(new AnimalSpeciesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, AnimalSpecies animalSpecies) {
                Intent intent = new Intent(getContext(), AnimalSpeciesActivity.class);
                intent.putExtra("animalSpecies", animalSpecies);
                startActivity(intent);
            }
        });
        animalSpeciesList = AppDataUtils.getAnimalSpeciesList();

        animalKnowledgeAdapter = new AnimalKnowledgeAdapter();
        animalKnowledgeAdapter.setOnItemClickListener(new AnimalKnowledgeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, AnimalKnowledge animalKnowledge) {
                Intent intent = new Intent(getContext(), AnimalKnowledgeActivity.class);
                intent.putExtra("animalKnowledge", animalKnowledge);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        rvAnimalSpecies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvAnimalSpecies.setAdapter(animalSpeciesAdapter);

        rvAnimalKnowledge.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAnimalKnowledge.setAdapter(animalKnowledgeAdapter);

        animalKnowledgeAdapter.setList(AppDataUtils.getAnimalKnowledgeList());

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRandomAnimal();
            }
        });
        setRandomAnimal();
    }

    public void setRandomAnimal() {
        //从animalSpeciesList中随机取出3个动物种类，并且不能重复
        List<AnimalSpecies> randomAnimalSpecies = new ArrayList<>();
        while (randomAnimalSpecies.size() < 3) {
            int randomIndex = (int) (Math.random() * animalSpeciesList.size());
            AnimalSpecies animalSpecies = animalSpeciesList.get(randomIndex);
            if (!randomAnimalSpecies.contains(animalSpecies)) {
                randomAnimalSpecies.add(animalSpecies);
            }
        }
        animalSpeciesAdapter.setList(randomAnimalSpecies);
        //rvAnimalSpecies回到顶部
        rvAnimalSpecies.scrollToPosition(0);
    }

}
