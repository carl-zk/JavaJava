package service;

import org.springframework.stereotype.Service;

/**
 * Created by hero on 17-4-2.
 */
@Service
public class TakePlane extends HHH{
    public void check(){
        System.out.println("YOU CAN TAKE ON PLANE");
    }

    public void print() {
        System.out.println("hll");
    }
}
