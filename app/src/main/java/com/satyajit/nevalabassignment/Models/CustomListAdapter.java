package com.satyajit.nevalabassignment.Models;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.satyajit.nevalabassignment.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Satyajit on 10/02/18
 */

public class CustomListAdapter extends ArrayAdapter<Person> {

    ArrayList<Person> persons;
    Context context;
    int resource;

    public CustomListAdapter(Context context, int resource, ArrayList<Person> persons) {
        super(context, resource, persons);
        this.persons = persons;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate( R.layout.persons_list, null, true);

        }
        final Person person = getItem(position);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.profPic);
        try{ Picasso.with(context).load(person.getImage()).into(imageView);
             }
            catch (Exception e){
                     e.printStackTrace ();
            }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(person.getName());

        TextView skills = (TextView) convertView.findViewById(R.id.skills);
        String upperString = person.getSkills ().substring(0,1).toUpperCase() + person.getSkills ().substring(1);
        skills.setText(upperString);

        return convertView;
    }
}

