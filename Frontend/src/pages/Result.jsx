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
import { useParams } from 'react-router-dom';

function Result (props) {
  let {lat1,lng1,lat2,lng2} = useParams();
  useEffect(() => {
    console.log(lat1,lat2,lng1,lng2);
  }, [])
  const [path, setPath] = useState(null)
  const [data, setData] = useState({
    loc1: {longitude:lng1,latitude:lat1},
    loc2: {longitude:lng2,latitude:lat2},
  });
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
      center={[lat1,lng1]}
      zoom={17}
      style={{ height: '100vh', width: '100%' }}
    >
      <TileLayer
        url='https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
        attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
      />
      <Marker position={[lat1,lng1]}>
          <Popup>
            Latitude: {lat1} <br /> Longitude: {lng1}
          </Popup>
      </Marker>
      <Marker position={[lat2,lng2]}>
          <Popup>
            Latitude: {lat2} <br /> Longitude: {lng2}
          </Popup>
      </Marker>
      <Marker position={path?[path[0].latitude,path[0].longitude]:[lat1,lng1]}>
          <Popup>
            Latitude: {lat1} <br /> Longitude: {lng1}
          </Popup>
      </Marker>
      <Marker position={path?[path[path.length-1].latitude,path[path.length-1].longitude]:[lat2,lng2]}>
          <Popup>
            Latitude: {lat2} <br /> Longitude: {lng2}
          </Popup>
      </Marker>
      <Polyline positions={path?path.map(e=>[e.latitude,e.longitude]):[[28.4510506, 77.0439059]]} color='blue' />
    </MapContainer>
  )
}

export default Result
