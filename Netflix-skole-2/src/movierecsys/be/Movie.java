/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.be;

/**
 *
 * @author pgn
 */
public class Movie
{

    private final int id;
    private String title;
    private int year;
    private double combinedRatingScore;
    private int counter=0;

    public Movie(int id, int year, String title)
    {
        this.id = id;
        this.title = title;
        this.year = year;
    }

    public Movie(int movieId, int userId, Rating rating)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    @Override
    public String toString()
    {
        return "Movie{" + "id=" + id + ", title=" + title + ", year=" + year + '}';
    }
    
    public void addRating(int rating){
        combinedRatingScore+=rating;
    }
    public void addCounter(){
        counter++;
    }
    
    public double getAverage(){
    double average = combinedRatingScore/counter;
    return average;
    }
}
