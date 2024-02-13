package cineticket;
import java.awt.Color;

import javax.swing.JButton;

public class SeatButton extends JButton{
	private SeatStatus status;

	public SeatStatus status() {
		return status;
	}

	public SeatStatus getStatus() {
		return status;
	}

	public void setStatus(SeatStatus status) {
		this.status = status;
	}

	public SeatButton(SeatStatus status) {
		super();
		this.status = status;
	}

	public SeatButton(String name) {
		super(name);
	}

	public SeatButton() {
		super();
	}

}
