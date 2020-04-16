package commands;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import gui.FreeTTSWindow;

public class TuneAudio implements ActionListener{
	
	private FreeTTSWindow frame;
	
	public TuneAudio(FreeTTSWindow frame) {
		this.frame = frame;
	}
		
	@Override
	public void actionPerformed(ActionEvent ev) {
		JFrame preferencesFrame = new JFrame();
		preferencesFrame.setTitle("Preferences");
		preferencesFrame.setBounds(400, 200, 482, 435);
		preferencesFrame.setResizable(false);
		preferencesFrame.setVisible(true);
						
		JLabel volumeLabel = new JLabel("Volume");		
		JLabel speedLabel = new JLabel("Speed");	
		JLabel pitchLabel = new JLabel("Pitch");
		JLabel encodingStrategyLabel = new JLabel("Encoding Strategy");
		
		JSlider volumeSlider = new JSlider();
		volumeSlider.setValue(frame.getVolumeValue());
		volumeSlider.setBorder(new LineBorder(SystemColor.activeCaption));		
		JSlider speedSlider = new JSlider();
		speedSlider.setValue(frame.getSpeedValue());
		speedSlider.setBorder(new LineBorder(SystemColor.activeCaption));		
		JSlider pitchSlider = new JSlider();
		pitchSlider.setValue(frame.getPitchValue());
		pitchSlider.setBorder(new LineBorder(SystemColor.activeCaption));			
		
		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				frame.setVolumeValue(volumeSlider.getValue());
				frame.setSpeedValue(speedSlider.getValue());
				frame.setPitchValue(pitchSlider.getValue());
				preferencesFrame.dispose();
				
				//TODO Add model.Document.saveSettings()
			}
		});
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				preferencesFrame.dispose();
			}
		});
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("None");
		comboBox.addItem("Atbash");
		comboBox.addItem("Rot-13");
		
		//...
		
		GroupLayout groupLayout = new GroupLayout(preferencesFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(297, Short.MAX_VALUE).addComponent(applyButton).addGap(18)
					.addComponent(cancelButton).addGap(33)).addGroup(groupLayout.createSequentialGroup()
					.addGap(50).addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
					.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(encodingStrategyLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(18).addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createSequentialGroup()
					.addComponent(pitchLabel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED).addComponent(pitchSlider, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createSequentialGroup()
					.addComponent(speedLabel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(speedSlider, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createSequentialGroup()
					.addComponent(volumeLabel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(volumeSlider, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(140, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(51).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(volumeSlider, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
				.addComponent(volumeLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(33).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(speedSlider, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
				.addComponent(speedLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(31).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(pitchSlider, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
				.addComponent(pitchLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup()
				.addGap(40).addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 73, Short.MAX_VALUE).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(cancelButton).addComponent(applyButton)).addGap(29)).addGroup(groupLayout.createSequentialGroup()
				.addGap(29).addComponent(encodingStrategyLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE).addContainerGap())))
		);
		preferencesFrame.getContentPane().setLayout(groupLayout);
	}
}