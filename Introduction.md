# A Brief Introduction


This project attempts to create a platform in which the general populace can help simplify the data pre-processing process. The average person, henceforth called the `user`, joins the data pre-preprocessing process with the guidance of Robotic Process Automation. The pre-processed data is then sent to entities in need of data (ex. AI developers, data engineers, etc), henceforth called `requester`s.

In comparison to existing players, our item filters data through `RPA` before sending the processed data to users for confirmation. Unlike the manual entering of data required in the present market, robotic process automation ensures that the data is already entered into the system; the user's role is merely check if the data has been input correctly.

Our RPA solution can be divided into four parts: 
1. Image Labelling Automation
2. OCR Labelling Automation
3. Video Labelling Automation
4. Sound Labelling Automation

<br>
<br>

## A Business Perspective

At the moment, `Amazon Mechanical Turk (AMT)` and `Crowdworks` remain the biggest players in the data pre-processing platform market. However, both are considered an unpopular source of data due to low accuracy rates on the part of data requesters, and an unpopular source of work due to inconvenient user interfaces and complicated incentive system on the part of users. In particular, AMT seems to lack an Android userface, forcing users to work in front of a computer screen and encounter a difficult customer experience. Furthermore, both platforms lack exposure to the public due to the lack of attempt to market the product to potential users in the general populace. 

In comparison to existing players, our item filters data through `RPA` before sending the processed data to users for confirmation. Unlike the manual entering of data required in the present market, robotic process automation ensures that the data is already entered into the system; the user's role is merely check if the data has been input correctly.

However, our current strategy is *not* to compete with abovementioned existing players, but to rather collaborate with them by offering our RPA solution to optimise their current platform system. Furthermore, other suggestions we would make would concern increasing interest in their platforms through marketing tactics such as advertisements in mobile games/apps, and simplifying their current payment systems through partnerships with payment platforms such as kakaopay.  
<br>
<br>

## Technical Details

1. Image Labelling Automation <br>
Image Labelling recognises objects within an image and prints the image format out as a text format; the text format simplifies the pre-processing process. Furthermore, YOLO V3 technology was used to amplify the accuracy of image recognition. 
<br> 
<br>
After being suggested several possible object choices, the user selects the most probable choice to finish the labelling process.
<br>
<br>
이미지 라벨링은 이미지 안에 오브젝트들을 파악하여 그것을 텍스트형태로 출력한다.텍스트 형태로 나와 전처리 하기 쉽게 하고, 오브젝트 인식률을 높이기 위하여 YOLO V3 기술을 사용하였다. 나올수 있는 여러가지 오브젝트 중에 한개를 선택하여 라벨링을 완료시킨다.
<br>
<br>

2. OCR Labelling Automation


<br>
<br>

3. Video Labelling Automation <br>
As an extension of the Image Labelling process, Video Labelling labels objects within an video, in the format of multiple images. YOLO V3 technology, specifically that which frames objects in the form of CenterNet, was used to maximise object recognition. 
<br>
<br>
Object data can be pre-processed from videos installed within transportation or CCTVs; this process can be carried out at a real-time rate of time. By adjusting the frame, we can prevent images from repeating and apply labels onto saved images. 
<br>
<br>
비디오 라벨링은 이미지 라벨링의 연장선으로써, 비디오를 마치 이미지처럼 여러장의 이미지라벨링을 하는것이다. CenterNet 형식의 오브젝트에 프레임을 씌우는 YOLO V3 기술을 사용하여 오브젝트 인식을 최대한으로 할수있다. 
자동차 위에 카메라 또는 CCTV 를 설치하여 동영상 안의 오브젝트들을 실시간으로 처리할수있다. 프레임을 조절하여 중복되는 이미지를 최대한 피하고, 저장된 이미지들을 이미지 라벨링을 시킨다.
<br>
<br>

4. Sound Labelling Automation


<br>
