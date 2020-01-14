# Photo-Realistic Facial Details Synthesis from Single Image (Oral paper ICCV 19')
---------
## Background
### 人脸识别 (face recognition)   2D vs 3D
3D has been shown that 3D face recognition methods can achieve significantly higher accuracy than their 2D counterparts, **rivaling** fingerprint recognition (according to WIKI). 2D案例，苹果无法识别双胞胎
1. This avoids such pitfalls of 2D face recognition algorithms as change in lighting, different facial expressions, make-up and head orientation
2. improve accuracy of traditional image based recognition by transforming the head into a known view
3. most 3D scanners acquire both a 3D mesh and the corresponding texture. This allows combining the output of pure 3D matchers with the more traditional 2D face recognition algorithms, thus yielding better performance
4. **DISADVANTAGE** the acquisition of 3D image, may need a **range camera** or **multiple** images from different angles from a common camera

### construct 3D face from a single picture
如何通过单张图片恢复高质量的三维人脸是计算机视觉和图形学的重要研究领域，高质量的3D人脸通常指准确的几何、完整的纹理和真实的材质。In this paper, we present a single-image 3D face synthesis technique that can handle challenging facial expressions while recovering fine geometric details.
1. proxy estimation
2. details synthesis
