package soundsystem;

import org.springframework.stereotype.Component;

/**
 * Created by hero on 10/04/2018.
 */
public class SoloAlbum implements CompactDisc {
    public void play() {
        System.out.println("个人专辑");
    }
}
