package org.example.engine;

import javax.swing.*;
import java.io.IOException;

public interface Game {
    void launch(JFrame frame) throws IOException;

    String name();
}