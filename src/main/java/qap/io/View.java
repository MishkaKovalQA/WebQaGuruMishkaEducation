package qap.io;

import javax.swing.*;

import static java.util.Objects.requireNonNull;

public interface View {

    String APP_NAME = "QAP";
    Icon DEER_ICON = new ImageIcon(requireNonNull(View.class.getClassLoader().getResource("icon/deer.png")));
    Icon SANTA_ICON = new ImageIcon(requireNonNull(View.class.getClassLoader().getResource("icon/sad_santa.png")));
}
