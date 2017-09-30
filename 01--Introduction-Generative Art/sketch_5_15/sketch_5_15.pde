import hype.*;

HImage d;

void setup() {
	size(600, 600);
	H.init(this).background(#202020);
	smooth();

	for (int i = 0; i < 100; ++i) {
		d = new HImage("data/image.png");
		d

			.size( (int)random(75,175) )
			.loc( (int)random(width), (int)random(height) )
			.anchorAt(H.CENTER)
			;
			H.add(d);
		}
	H.drawStage();
}
