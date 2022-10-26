// React
import { useEffect } from 'react'

// Next
import { useRouter } from 'next/router'

function Home() {
  const router = useRouter()
  useEffect(() => {
    router.push('/tasks')
  }, [])
  return null
}

export default Home