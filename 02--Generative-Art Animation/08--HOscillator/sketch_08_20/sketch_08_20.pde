import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
// import hype.interfaces.*;

HDrawablePool pool;
HColorPool colors;

void setup() {
	size(640,640);
	H.init(this).background(#202020);
	smooth();

	colors = new HColorPool( #FFFFFF, #F7F7F7, #ECECEC, #333333, #0095A8, #00616f, #FF3300, #FF6600);

	pool = new HDrawablePool(144);
	pool.autoAddToStage()

		.add(new HShape("svg1.svg"))
		.add(new HShape("svg2.svg"))
		.add(new HShape("svg3.svg"))
		.add(new HShape("svg4.svg"))
		.add(new HShape("svg5.svg"))
		.add(new HShape("svg6.svg"))


	.layout (
		new HGridLayout()
		.startX(50)
		.startY(50)
		.spacing(50, 50)
		.cols(12)
		)


	.onCreate (
		new HCallback() {
			public void run(Object obj) {
				HShape d = (HShape) obj;
				d
					.enableStyle(false)
					.noStroke()
					.rotate( (int)random(4) * 90 )
					.size( 50 + ( (int)random(4) * 50 ) ) // 50, 100, 150, 200
				;

				d.randomColors(colors.fillOnly());

				new HOscillator()
					.target(d)
					.property(H.ROTATION)
					.range(-180, 180)
					.speed(0.8)
					.freq(5)
					.currentStep(pool.currentIndex())	
				;


				new HOscillator()
					.target(d)
					.property(H.SCALE)
					.range(1, 5)
					.speed(0.8)
					.freq(7)
					.currentStep(pool.currentIndex()*3)	
				;



			}
		}
	)

		.requestAll()
	;
}



void draw() {

H.drawStage();

	
}






