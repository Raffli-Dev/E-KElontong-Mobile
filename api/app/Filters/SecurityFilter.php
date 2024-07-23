<?php

namespace App\Filters;

use App\Config\Custom;
use CodeIgniter\Config\Services;
use CodeIgniter\Filters\FilterInterface;
use CodeIgniter\HTTP\RequestInterface;
use CodeIgniter\HTTP\ResponseInterface;

class SecurityFilter implements FilterInterface
{

    public function before(RequestInterface $request, $arguments = null)
    {
        $this->config_custom = new Custom();
        $security = $request->getHeader("Security");

        if (!$security) {
            return $this->showResponse('required security header');
        } else if ($security->getValue() != $this->config_custom->SecurityCode) {
            return $this->showResponse('invalid security header');
        }
    }

    public function after(RequestInterface $request, ResponseInterface $response, $arguments = null)
    {
        // TODO: Implement after() method.
    }
    protected function showResponse($message){
        $resp = ['status' => 401, 'messages' => $message];
        $response = Services::response();
        $response->setStatusCode(401);
        $response->setJSON($resp);
        return $response;
    }
}