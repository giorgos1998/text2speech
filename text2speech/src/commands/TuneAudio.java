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
		JLabel speechLibrarylabel = new JLabel("Speech Library");
		
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
		comboBox.addItem("ROT13");
		comboBox.addItem("ATBASH");
		comboBox.setSelectedItem(frame.getEncodingStrategy());
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.addItem("FREETTS");
		comboBox_1.addItem("FAKETTS");
		comboBox_1.setSelectedItem(frame.getSpeechLibrary());
		
		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				int volumeValue = volumeSlider.getValue();
				int speedValue = speedSlider.getValue();
				int pitchValue = pitchSlider.getValue();
				String encodingStrategy = String.valueOf(comboBox.getSelectedItem());
				String speechLibrary = String.valueOf(comboBox_1.getSelectedItem());
				
				frame.setVolumeValue(volumeValue);
				frame.setSpeedValue(speedValue);
				frame.setPitchValue(pitchValue);
				frame.setEncodingStrategy(encodingStrategy);
				frame.setSpeechLibrary(speechLibrary);
				preferencesFrame.dispose();
				
				//TODO Add model.Document.saveSettings()
				doc.saveSettings(volumeValue, speedValue, pitchValue, encodingStrategy, speechLibrary);
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
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(307, Short.MAX_VALUE)
				.addComponent(applyButton).addGap(18).addComponent(cancelButton).addGap(33))
				.addGroup(groupLayout.createSequentialGroup().addGap(50).addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
				.addComponent(speechLibrarylabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
				.addComponent(encodingStrategyLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGap(18).addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
				.addComponent(pitchLabel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(pitchSlider, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
				.addComponent(speedLabel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(speedSlider, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
				.addComponent(volumeLabel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(volumeSlider, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.RELATED))).addContainerGap(150, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(42).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(volumeSlider, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
				.addComponent(volumeLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(33).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(speedSlider, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
				.addComponent(speedLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(31).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(pitchSlider, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
				.addComponent(pitchLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(29).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(encodingStrategyLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE).addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(speechLibrarylabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(29).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(cancelButton)
				.addComponent(applyButton)).addGap(29))
		);
		preferencesFrame.getContentPane().setLayout(groupLayout);
	}
}