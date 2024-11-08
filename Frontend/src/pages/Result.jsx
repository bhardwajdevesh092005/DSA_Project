import React, { useEffect, useState } from 'react'
import {
  MapContainer,
  TileLayer,
  Marker,
  Popup,
  useMapEvents,
  Polyline
} from 'react-leaflet'
import 'leaflet/dist/leaflet.css'

function Result () {
  const [positions1, setPositions1] = useState([
    [77.021087, 28.4963019],
    [77.0210266, 28.4963278],
    [77.0209609, 28.4963903],
    [77.0209197, 28.4965046],
    [77.0209086, 28.4966932],
    [77.0209073, 28.496936],
    [77.0208979, 28.4969784],
    [77.0208751, 28.4970091],
    [77.0208254, 28.4970185],
    [77.0204942, 28.4970232],
    [77.0200557, 28.4970232],
    [77.0199466, 28.4970264],
    [77.0198901, 28.4970368],
    [77.0198553, 28.4970645],
    [77.0198411, 28.4971175],
    [77.0198796, 28.497446],
    [77.0199055, 28.4974746],
    [77.019935, 28.497487],
    [77.02005, 28.4974953]
  ])
  const [positions2, setPositions2] = useState([
    [77.0432859, 28.450458],
    [77.0434847, 28.4506002],
    [77.043639, 28.4507145],
    [77.0437449, 28.4508148],
    [77.0437999, 28.4508631],
    [77.0438348, 28.4509138],
    [77.0438723, 28.4509834],
    [77.0439059, 28.4510506],
    [77.043981, 28.4512581],
    [77.0440319, 28.4514869],
    [77.0440546, 28.4516844],
    [77.0440561, 28.451751],
    [77.0440437, 28.4517899],
    [77.0440185, 28.4518288],
    [77.0439112, 28.4519114],
    [77.0437478, 28.4520255]
  ])
  useEffect(() => {
    setPositions1(prev => prev.map(elem => [elem[1], elem[0]]))
    setPositions2(prev => prev.map(elem => [elem[1], elem[0]]))
  }, [])
  // setPositions((prev)=>(prev.map(elem=>[elem[1],elem[0]])));
  const [path, setPath] = useState(null)
  const [data, setData] = useState({
    loc1: { longitude:77.0432859, latitude:28.450458 },
    loc2: { longitude:77.0437478, latitude:28.4520255}
  })
  const data_call = async () => {
    await fetch('http://localhost:5174/data', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json' // Specify the content type
      },
      body: JSON.stringify(data)
    })
      .then(res => res.json())
      .then(data => {
        setPath(data.result.length?data.result:null);
        console.log(data.result);
        // console.log(data.result[0].latitude);
      })
  }
  useEffect(() => {
    data_call()
    return () => {
      console.log(123)
    }
  }, [123])
  //   data_call();
  return (
    <MapContainer
      center={path?[path[0].latitude,path[0].longitude]:[28.4510506, 77.0439059]}
      zoom={23}
      style={{ height: '100vh', width: '100%' }}
    >
      <TileLayer
        url='https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
        attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
      />
      <Polyline positions={path?path.map(e=>[e.latitude,e.longitude]):[[28.4510506, 77.0439059]]} color='blue' />
      {/* <Polyline positions={positions2} color='blue' /> */}
    </MapContainer>
  )
}

export default Result
