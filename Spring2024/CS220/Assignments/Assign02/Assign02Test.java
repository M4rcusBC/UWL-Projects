
public class Assign02Test {

	public static void main(String[] args) {

		/* UNCOMMENT THESE VARIABLES AS NEEDED FOR TESTS */
		StarTV starTv = new StarTV();
		NebulaTV nebulaTv = new NebulaTV();
		// P8 p8 = new P8();
		// P10 p10 = new P10();
		
		/* Test 1: Testing for StarTV */
		System.out.println("** TEST 1 **");

		starTv.decChannel();
		starTv.incChannel();
		starTv.changeChannel(100);
		starTv.incVolume();
		starTv.decVolume();

		System.out.println(starTv + "\n\n");

		/* Test 2: Testing for NebulaTV */
		System.out.println("** TEST 2 **");

		nebulaTv.decChannel();
		nebulaTv.incChannel();
		nebulaTv.changeChannel(100);
		nebulaTv.incVolume();
		nebulaTv.decVolume();

		System.out.println(nebulaTv + "\n\n");

		/* Test 3: Testing for NebulaTV voice interface */
		System.out.println("** TEST 3 **");
		
		try {
			nebulaTv.processSpeech("increase channel");
			nebulaTv.processSpeech("decrease channel");
			nebulaTv.processSpeech("increase volume");
			nebulaTv.processSpeech("decrease volume");
			nebulaTv.processSpeech("change 50");
			nebulaTv.processSpeech("hello tv");
		} catch (SpeechNotUnderstoodException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(nebulaTv + "\n\n");

		/* Test 4: Testing for P8 */
//		System.out.println("** TEST 4 **");
//
//		p8.takePicture();
//		p8.talkOnPhone(10);
//		p8.charge();
//
//		System.out.println(p8 + "\n\n");

		/* Test 5: Testing for P8 voice interface */
//		System.out.println("** TEST 5 **");
//		
//		try {
//			p8.processSpeech("picture");
//			p8.processSpeech("talk 5");
//			p8.processSpeech("video 30");
//		} catch (SpeechNotUnderstoodException e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println(p8 + "\n\n");

		/* Test 6: Testing for P10 */
//		System.out.println("** TEST 6 **");
//		
//		p10.takePicture();
//		p10.talkOnPhone(10);
//		p10.takeVideo(15);
//		p10.charge();
//
//		System.out.println(p10 + "\n\n");

		/* Test 7: Testing for P10 voice interface */
//		System.out.println("** TEST 7 **");
//		
//		try {
//			p10.processSpeech("picture");
//			p10.processSpeech("talk 5");
//			p10.processSpeech("video 30");
//			p10.processSpeech("camera 15");
//		} catch (SpeechNotUnderstoodException e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println(p10 + "\n\n");

		/* Test 8: Testing TV Type Conformance */
//		System.out.println("** TEST 8 **");
//		
//		TV[] tvs = new TV[2];
//		tvs[0] = starTv;
//		tvs[1] = nebulaTv;
//		
//		for (int i = 0; i < tvs.length; ++i) {
//			System.out.println(tvs[i]);
//		}
//		
//		for (int i = 0; i < tvs.length; ++i) {
//			tvs[i].changeChannel(13);
//		}
//		
//		for (int i = 0; i < tvs.length; ++i) {
//			System.out.println(tvs[i]);
//		}
//		
//		System.out.println("\n\n");
		
		/* Test 9: Testing Smartphone Type Conformance */
//		System.out.println("** TEST 9 **");
//		
//		Smartphone[] smrtphs = new Smartphone[2];
//		smrtphs[0] = p8;
//		smrtphs[1] = p10;
//		
//		for (int i = 0; i < smrtphs.length; ++i) {
//			smrtphs[i].saySomething("This is the " + smrtphs[i].toString());
//			try {
//				smrtphs[i].processSpeech("talk 10");
//			} catch (SpeechNotUnderstoodException e) {
//				e.printStackTrace();
//			}
//			smrtphs[i].saySomething("This is the " + smrtphs[i].toString());
//			smrtphs[i].charge();
//		}
//		
//		System.out.println("\n\n");

		/* Test 10: Testing VoiceAssistant Interface Type Conformance */
//		System.out.println("** TEST 10 **");
//		
//		VoiceAssistant[] vas = new VoiceAssistant[3];
//		vas[0] = nebulaTv;
//		vas[1] = p8;
//		vas[2] = p10;
//
//		for (int i = 0; i < vas.length; ++i) {
//			vas[i].saySomething("This is the " + vas[i].toString());
//		}
//		
//		System.out.println("\n\n");

		/* Test 11: Testing Logger Interface Type Conformance */
//		System.out.println("** TEST 11 **");
//		
//		Logger[] loggerDevices = new Logger[4];
//		loggerDevices[0] = starTv;
//		loggerDevices[1] = nebulaTv;
//		loggerDevices[2] = p8;
//		loggerDevices[3] = p10;
//
//		for (int i = 0; i < loggerDevices.length; ++i) {
//			loggerDevices[i].writeToLogFile("Testing logging functionality");
//		}

	}

}
