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
import model.Document;

/**
 * <h1> Tune Audio Command </h1> 
 * 
 * Creates the "Preferences" window that contains
 * the sliders that set the values of volume, speed, pitch 
 * and encoding strategy of the speech.
 * You can either press the "Apply" button to save any changes 
 * made or press the "Cancel" button to discard them.
 * 
 * @author Vasiliki Kanakari
 */


public class TuneAudio implements ActionListener{
	
	private FreeTTSWindow frame;
	private Document doc;
	
	public TuneAudio(FreeTTSWindow frame, Document doc) {
		this.frame = frame;
		this.doc = doc;
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
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("None");
		comboBox.addItem("Atbash");
		comboBox.addItem("Rot-13");
		comboBox.setSelectedItem(frame.getEncodingStrategy());
		
		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				int volumeValue = volumeSlider.getValue();
				int speedValue = speedSlider.getValue();
				int pitchValue = pitchSlider.getValue();
				String encodingStrategy = String.valueOf(comboBox.getSelectedItem());
				
				frame.setVolumeValue(volumeValue);
				frame.setSpeedValue(speedValue);
				frame.setPitchValue(pitchValue);
				frame.setEncodingStrategy(encodingStrategy);
				preferencesFrame.dispose();
				
				//TODO Add model.Document.saveSettings()
				doc.saveSettings(volumeValue, speedValue, pitchValue, encodingStrategy);
			}
		});
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				preferencesFrame.dispose();
			}
		});
				
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