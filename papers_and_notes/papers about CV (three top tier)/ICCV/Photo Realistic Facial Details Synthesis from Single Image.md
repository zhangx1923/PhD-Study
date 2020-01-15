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
常用的方法：
Successful solutions by far rely on complex and often expensive capture systems such as stereo-based camera domes or photometric-based LightStage.

如何通过单张图片恢复高质量的三维人脸是计算机视觉和图形学的重要研究领域，高质量的3D人脸通常指准确的几何、完整的纹理和真实的材质。

#### Single-image solutions (basic idea)
1. construct a 3D proxy face from templates
2. refine the proxy by deforming geometry and adding details
Problem: such as over-smoothing and incorrect expression (因为real face需要高维的数据，但构造使用的参数太少)

**Shape-from-shading**, **photometric stereo**, and **deep learning** have been used to generate the missing details

In this paper, we aim to produce high-quality 3D faces with fine geometric details from a single image, with quality comparable to those produced from the dome systems and LightStage
1. Step one: proxy estimation
2. Step two: details synthesis


### some concepts in this paper
1. Geometric structure details: such as wrinkles are important indicators of age and facial expression, and are essential for producing realistic virtual human
2. 3D Proxy: A proxy graphic is a placeholder that represents the object. This placeholder does not have the full display or attributes of the object可以理解成一个人脸的3D模型，之后再在这上面去添加细节信息

## Related Work
- Reconstruction-based Techniques
- Synthesis-based approaches
