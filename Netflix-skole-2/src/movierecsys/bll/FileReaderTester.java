/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.bll;

import movierecsys.dal.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;

/**
 *
 * @author pgn
 */
public class FileReaderTester
{

    /**
     * Example method. This is the code I used to create the users.txt files.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {

        MRSManager test = new MRSManager();
//        test.getAllTimeTopRatedMovies();
        User userTest = new User(1150912,"Douadi Eri");
        
        test.getMovieReccomendations(userTest);
        
                
              
        
        
        
    
        
        
        
        
    }
}
