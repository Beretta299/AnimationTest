# AnimationTest
1. Main container with animation supports pitch gesture (zoom in zoom out)
2. I struggled with the animation duration, because I didn't found a way to control speed of animation programmatically. There is 2 ways which I found out working. First is the using SeekableAnimatedVectorDrawable, and adding timers for execution, which I used. Second is the using reflection, but maybe I just didn't found some normal solution for this.
