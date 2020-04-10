package textTospeechAPI;

public class FakeTTSadapter implements TextToSpeechApi {

	public void play(String textToPlay) {
		System.out.println("This is a fake text to speech, it can only convert text to text.");
		System.out.println("It's even more useless because this does not get printed in the UI.");
		System.out.println("Anyway here's your text:");
		System.out.println(textToPlay);
	}
	public void setVolume(int volume) {
		System.out.println("This is a fake text to speech, it can not produce sound.");
		System.out.println("Volume is a property of sound, you tried to change it to: "+ volume);
	}
	public void setPich(double pitch) {
		System.out.println("This is a fake text to speech, it can not produce sound.");
		System.out.println("Pitch is a property of sound, you tried to change it to: "+ pitch);
	}
	public void setSpeed(double speed) {
		System.out.println("This is a fake text to speech, it can not produce sound.");
		System.out.println("Speed is a property of sound, you tried to change it to: "+ speed);
	}
}
