package main;

import javax.swing.*;

public class Card extends JButton {

    private String name;
    private boolean hidden;
    private boolean viewing;
    private boolean matched;
    private ImageIcon cardIcon;

    public Card() {
        this.hidden = true;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object object){
        Card card = (Card) object;
        return this.name.equals(card.name);
    }

    public boolean isMatch(Object object){
        Card card = (Card) object;
        return (this.isViewing() && card.isViewing()) && this.name.equals(card.name);
    }

    public boolean isHidden() {
        return hidden;
    }

    public boolean isViewing() {
        return viewing;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void setViewing(boolean viewing) {
        this.viewing = viewing;
    }

    public ImageIcon getCardIcon() {
        return cardIcon;
    }

    public void setCardIcon(ImageIcon cardIcon) {
        this.cardIcon = cardIcon;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }


}
