import React, { useEffect, useState } from "react";
import MainCard from "./components/MainCard";
import styled from "styled-components";
import tw from "twin.macro";
import "./main.css";
// import axios from "axios";

const MainPage = () => {
  

  // const getProjects = () => {
  //   axios({
  //     url: "/api/project",
  //     method: "GET",
  //   })
  //   .then(res => {
  //     console.log(res.response)
  //   })
  // }
  

  // getProjects()

  const cards = useState([
    {
      "id": 0,
      "projectReqDto": {
        "category": "CRANE",
        "subject": "다람쥐와 도토리1",
        "description": "다람쥐는 오늘도 도토리를 찾아 헤맵니다..."
      },
      "donationResDto": {
        "startDate": "2023-03-24",
        "endDate": "2023-03-27",
        "targetAmount": 1000000,
        "subject": "다람쥐에게 도토리를 주세요",
        "description": "먹이가 필요한 다람쥐에게 도토리를 전달하는 기부입니다",
        "id": 0,
        "amount": 0
      },
      "style": () => "background-color: red;"
    },
    {
      "id": 1,
      "projectReqDto": {
        "category": "CRANE",
        "subject": "다람쥐와 도토리2",
        "description": "다람쥐는 오늘도 도토리를 찾아 헤맵니다..."
      },
      "donationResDto": {
        "startDate": "2023-03-24",
        "endDate": "2023-03-27",
        "targetAmount": 1000000,
        "subject": "다람쥐에게 도토리를 주세요",
        "description": "먹이가 필요한 다람쥐에게 도토리를 전달하는 기부입니다",
        "id": 0,
        "amount": 0
      },
      "style": () => "background-color: green;"
    },
    {
      "id": 2,
      "projectReqDto": {
        "category": "CRANE",
        "subject": "다람쥐와 도토리3",
        "description": "다람쥐는 오늘도 도토리를 찾아 헤맵니다..."
      },
      "donationResDto": {
        "startDate": "2023-03-24",
        "endDate": "2023-03-27",
        "targetAmount": 1000000,
        "subject": "다람쥐에게 도토리를 주세요",
        "description": "먹이가 필요한 다람쥐에게 도토리를 전달하는 기부입니다",
        "id": 0,
        "amount": 0
      },
      "style": () => "background-color: blue;"
    }

  ]);
  return (
    <Container>
        {
          cards[0].map((card, index) => { 
            return (
              <MainCard card={card} key={index} />
            )
          })
        }
        <Div>
          <h1>여기는 NFT</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>1111</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>333333333333333333333</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>
          <h1>111111111111555555555555555555555555555</h1>

        </Div>
    </Container>
  );
};


const Container = styled.div`
width: 100vw;
padding-top: 64px;
height: 100vh;
overflow: auto;
${tw`snap-y snap-mandatory scrollbar-hide`}
`

const Div = styled.div`
height: 1500px;
width: 100%;
${tw`snap-none`}
`
export default MainPage;
