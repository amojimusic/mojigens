MojiDistort {


	*ar {
		arg signal, wet = 0.5, drive = 0.5, lowDrive = 0.5,  highDrive = 0.5, bandAdd = 0.0;
		var sig, del, b1, b2, b3, b4, b5, dr, hDr, lDr, b;

		sig = signal;
		dr = drive * 800;
		hDr = highDrive * 5;
		lDr = lowDrive * 5;
		b = bandAdd;
		b = b.clip2(5000.0);



		del = SinOsc.kr(SinOsc.kr(4.4, 0, rrand(0.005, 0.4)).abs, 0, 0.4).abs + 0.1 * (dr * lDr);
		b1 = (LPF.ar(sig, 500 + b) * (1 + del.abs)).softclip;
		del = SinOsc.kr(SinOsc.kr(SinOsc.kr(1.5, 0, 20).abs, 0, rrand(0.005, 0.4)).abs, 0, 0.4) + 1 * dr;
		b2 = (LPF.ar(HPF.ar(sig, 500 + b), 1000 + b) * (1 + del.abs)).softclip;
		del = SinOsc.kr(SinOsc.kr(SinOsc.kr(2.65, 0, 10.2).abs, 0, rrand(0.005, 0.4)).abs, 0, 0.4).abs + 0.1 * dr;
		b3 = (LPF.ar(HPF.ar(sig, 1000 + b), 1500 + b) * (1 + del.abs)).softclip;
		del = SinOsc.kr(SinOsc.kr(SinOsc.kr(0.25, 0, 20.2).abs, 0, rrand(0.5, 0.4)).abs, 0, 0.4).abs + 0.1 * dr;
		b4 = (LPF.ar(HPF.ar(sig, 1500 + b), 2000 + b) * (1 + del.abs)).softclip;
		del = SinOsc.kr(SinOsc.kr(SinOsc.kr(3.15, 0, 100.5).abs, 0, rrand(0.5, 0.4)).abs, 0, 0.4).abs + 0.1 * (dr * hDr);
		b5 = (HPF.ar(sig, 2000 + b) * (1 + del.abs)).softclip;
		b1 = b1 * 1.8;
		b5 = b5 * 1.4;
		b1 = b1.blend(b2, 0.5) * 0.5;
		b3 = b3.blend(b4, 0.5) * 0.5;
		b1 = b1.blend(b3, 0.5) * 0.5;
		b5 = b1.blend(b5, 0.125) * 0.5;
		b5 = b5 * 0.4;
		sig = sig.blend(b5, wet) * 0.9;
		sig = sig * (1 + wet);
		sig = Limiter.ar(sig, 1.0);
		^sig

	}


}

MojiDistort2 {


	*ar {
		arg signal, wet = 0.5, band1Drive = 0.0, band2Drive = 0.0, band3Drive = 0.0, band4Drive = 0.0, band5Drive = 0.0, bandAdd = 0.0;
		var sig, b1, b2, b3, b4, b5, b1D, b2D, b3D, b4D, b5D, b;

		sig = signal;
		b1D = band1Drive * 30;
		b2D = band1Drive * 30;
		b3D = band1Drive * 30;
		b4D = band1Drive * 30;
		b5D = band1Drive * 30;
		b = bandAdd;
		b = b.clip2(5000.0);


		b1 = (LPF.ar(sig, 500 + b) * (1 + b1D)).softclip;
		b2 = (LPF.ar(HPF.ar(sig, 500 + b), 1000 + b) * (1 + b2D)).softclip;
		b3 = (LPF.ar(HPF.ar(sig, 1000 + b), 1500 + b) * (1 + b3D)).softclip;
		b4 = (LPF.ar(HPF.ar(sig, 1500 + b), 2000 + b) * (1 + b4D)).softclip;
		b5 = (HPF.ar(sig, 2000 + b) * (1 + b5D)).softclip;
		b1 = b1.blend(b2, 0.5) * 0.5;
		b3 = b3.blend(b4, 0.5) * 0.5;
		b1 = b1.blend(b3, 0.5) * 0.5;
		b5 = b1.blend(b5, 0.125);
		sig = sig.blend(b5, wet);
		sig = sig * (1 + wet);
		sig = Limiter.ar(sig, 1.0);
		^sig

	}


}


MojiDistort3 {


	*ar {
		arg signal, wet = 0.5, band1Drive = 0.0, band2Drive = 0.0, band3Drive = 0.0, band4Drive = 0.0, band5Drive = 0.0, low = -1.0, high = 1.0, bandAdd = 0;
		var sig, b1, b2, b3, b4, b5, b1D, b2D, b3D, b4D, b5D, b;

		sig = signal;
		b1D = band1Drive * 30;
		b2D = band1Drive * 30;
		b3D = band1Drive * 30;
		b4D = band1Drive * 30;
		b5D = band1Drive * 30;
		b = bandAdd;
		b = b.clip2(5000.0);


		b1 = Clip.ar((LPF.ar(sig, 500 + b) * (1 + b1D)), low, high);
		b2 = Clip.ar((LPF.ar(HPF.ar(sig, 500 + b), 1000 + b) * (1 + b2D)), low, high);
		b3 = Clip.ar((LPF.ar(HPF.ar(sig, 1000 + b), 1500 + b) * (1 + b3D)), low, high);
		b4 = Clip.ar((LPF.ar(HPF.ar(sig, 1500 + b), 2000 + b) * (1 + b4D)), low, high);
		b5 = Clip.ar((HPF.ar(sig, 2000 + b) * (1 + b5D)), low, high);
		b1 = b1.blend(b2, 0.5) * 0.5;
		b3 = b3.blend(b4, 0.5) * 0.5;
		b1 = b1.blend(b3, 0.5) * 0.5;
		b5 = b1.blend(b5, 0.125);
		sig = sig.blend(b5, wet);
		sig = sig * (1 + wet);
		sig = Clip.ar(sig, -1.0, 1.0);
		^sig

	}


}


MojiCompressor {

	*ar {
		arg signal, wet = 0.5, thresh = 0.8, ratio = 0.4, attack = 0.1, release = 0.1, makeUp = 0.2;
		var sig, comp;
		sig = signal;
		comp = EnvGen.kr(Env.perc(attack, release, ((sig - thresh) * ratio)), Clip.ar(sig - thresh, 0));
		sig = sig.blend(sig * (1 - comp) * (1 + makeUp), wet);

		^sig;
	}


}