import http from 'k6/http';

export const options = {
      stages: [
        { duration: '30s', target: 1000 }, // Ramp to 20 users
        { duration: '1m', target: 10000 },  // Stay for 1 minute
        { duration: '10s', target: 0 },  // Ramp down
      ],
    };

export default function () {
  const url = 'http://localhost:8080/api/order';
  const payload = JSON.stringify({
      customerName: "string",
      productName: "string",
      quantity: 10
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };
  http.post(url, payload, params);
}