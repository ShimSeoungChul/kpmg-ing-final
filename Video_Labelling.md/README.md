# Yolo v3 를 활용한 오브젝트 인식
Yolo v3 는 딥러닝의 한 종류로써 오브젝트를 인식하는데 쓰인다. <br> <br>

## 시작단계

### 준비물
 Tensorflow (deep learning), NumPy (numerical computing), Pillow (image processing), OpenCV (computer vision) and seaborn (visualization)

```
pip install -r requirements.txt
```

### yolo v3 에 필요한 weight 를 다운로드하기
COCO dataset 을 다운로드 하기 

```
wget -P weights https://pjreddie.com/media/files/yolov3.weights
```

### Tensorflow 폼에 맞게 저장하기
 `load_weights.py` script 를 써서 저장하기

```
python load_weights.py
```

## 모델 실행하기
 `detect.py` script 을 써서 모델 실행한다. IoU 와 Threshhold 세팅하기
### Iou,threshold, detection 실행
```
python detect.py <images/video> <iou threshold> <confidence threshold> <filenames>
```
### 이미지 실행 

```
python detect.py images 0.5 0.5 data/images/dog.jpg data/images/office.jpg
```
 `detections` 폴더에 저장.
<br>
실행 결과.
```
detection_1.jpg
```
![alt text](https://github.com/heartkilla/yolo-v3/blob/master/data/detection_examples/detection_1.jpg)
```
detection_2.jpg
```
![alt text](https://github.com/heartkilla/yolo-v3/blob/master/data/detection_examples/detection_2.jpg)
### 비디오 실행
비디오로 오브젝트 인식
```
python detect.py video 0.5 0.5 data/video/shinjuku.mp4
```
                 `detections.mp4` 파일로 저장
![alt text](https://github.com/heartkilla/yolo-v3/blob/master/data/detection_examples/detections.gif)

## To-Do List
* Model training

## Acknowledgments
* [Yolo v3 official paper](https://arxiv.org/abs/1804.02767)
* [A Tensorflow Slim implementation](https://github.com/mystic123/tensorflow-yolo-v3)
* [ResNet official implementation](https://github.com/tensorflow/models/tree/master/official/resnet)
* [DeviceHive video analysis repo](https://github.com/devicehive/devicehive-video-analysis)
* [A Street Walk in Shinjuku, Tokyo, Japan](https://www.youtube.com/watch?v=kZ7caIK4RXI)
